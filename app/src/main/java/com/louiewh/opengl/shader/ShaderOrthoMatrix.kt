package com.louiewh.opengl.shader

import android.opengl.GLES20
import android.opengl.GLES30
import android.opengl.Matrix
import android.util.Log
import java.nio.ByteBuffer
import java.nio.ByteOrder
import java.nio.FloatBuffer
import javax.microedition.khronos.opengles.GL10

class ShaderOrthoMatrix: BaseShader() {

    private var vPosition = 0
    private var inColor = 0
    private var uMatrix = 0;

    private var VBO = 0
    private var VAO = 0

    private var aspectRatioX = 1.0f
    private var aspectRatioY = 1.0f

        private  val verticesSource =
        "attribute vec3 vPosition;                      \n" +
                "attribute    vec3 inColor;             \n" +
                "varying vec4 outColor;                 \n" +
                "uniform mat4 uMatrix;                  \n" +
                "void main(){                           \n" +
                "vec4 position = vec4(vPosition, 1.0);  \n" +
                "gl_Position = uMatrix * position;      \n" +
                "outColor    = vec4(inColor, 1.0);      \n" +
                "}"

    private  val fragmentSource =
        "precision mediump float;                       \n" +
                "varying vec4 outColor;                 \n" +
                "void main(){                           \n" +
                "gl_FragColor = outColor;               \n" +
                "}"

    private val mvpMatrix = floatArrayOf(
        1f, 0f, 0f, 0f,
        0f, 1f, 0f, 0f,
        0f, 0f, 1f, 0f,
        0f, 0f, 0f, 1f
    )

    override fun onInitGLES(program: Int) {
        vPosition = GLES20.glGetAttribLocation(program, "vPosition")
        inColor   = GLES20.glGetAttribLocation(program, "inColor")
        uMatrix   = GLES20.glGetUniformLocation(program, "uMatrix")

        initVBO()
        initVAO()

        GLES30.glUseProgram(getShaderProgram())
    }

    override fun onSurfaceChanged(gl: GL10?, width: Int, height: Int) {
        super.onSurfaceChanged(gl, width, height)

        val aspectRatio = if (width > height) {
            width.toFloat() / height
        } else {
            height.toFloat() / width
        }

        // 1. 矩阵数组
        // 2. 结果矩阵起始的偏移量
        // 3. left：x的最小值
        // 4. right：x的最大值
        // 5. bottom：y的最小值
        // 6. top：y的最大值
        // 7. near：z的最小值
        // 8. far：z的最大值
        // 由于是正交矩阵，所以偏移量为0，near 和 far 也不起作用
        if (width > height){
            Matrix.orthoM(mvpMatrix, 0, -aspectRatio, aspectRatio, -1f, 1f, -1f, 1f)
        }else{
            Matrix.orthoM(mvpMatrix,0, -1f, 1f, -aspectRatio, aspectRatio, -1f, 1f)
        }

        //更新 matrix 的值，即把 UnitMatrix 值，更新到 uMatrix 这个索引
        GLES30.glUniformMatrix4fv(uMatrix,1,false, mvpMatrix,0)
    }

    override fun onDestroyGLES() {
        GLES20.glDeleteBuffers(1, IntArray(VAO), 0)
        GLES20.glDeleteBuffers(1, IntArray(VBO), 0)
    }

    override fun getVertexSource(): String {
        return verticesSource
    }

    override fun getFragmentSource(): String {
        return fragmentSource
    }

    override fun onDrawFrame(gl: GL10?) {
        GLES30.glClear(GLES30.GL_DEPTH_BUFFER_BIT or GLES30.GL_COLOR_BUFFER_BIT)
        GLES30.glUseProgram(getShaderProgram())

        GLES30.glBindVertexArray(VAO)
        GLES30.glDrawArrays(GLES30.GL_TRIANGLE_STRIP, 0, 3)

        GLES30.glBindVertexArray(GLES30.GL_NONE)
    }

    private fun initVBO() {
        val vertices = getVertices()

        val intArray = IntArray(1)
        GLES30.glGenBuffers(1, intArray, 0)
        VBO = intArray[0]

        GLES30.glBindBuffer(GLES30.GL_ARRAY_BUFFER, VBO)
        GLES30.glBufferData(GLES30.GL_ARRAY_BUFFER, vertices.capacity() * VERTICES_FLOAT_SIZE, vertices, GLES30.GL_STREAM_DRAW)
        GLES30.glBindBuffer(GLES30.GL_ARRAY_BUFFER, GLES30.GL_NONE)
    }

    private fun initVAO() {
        val intArray = IntArray(1)
        GLES30.glGenVertexArrays(1, intArray, 0)
        VAO = intArray[0]

        //绑定VAO对象, VBO 的操作记录在VAO中
        GLES30.glBindVertexArray(VAO)

        // VBO bind
        GLES30.glBindBuffer(GLES30.GL_ARRAY_BUFFER, VBO)
        GLES30.glVertexAttribPointer(vPosition, 3, GLES30.GL_FLOAT, false, 6 * VERTICES_FLOAT_SIZE, 0)
        GLES30.glEnableVertexAttribArray(vPosition)

        GLES30.glVertexAttribPointer(inColor, 3, GLES30.GL_FLOAT, false, 6 * VERTICES_FLOAT_SIZE, 3 * VERTICES_FLOAT_SIZE)
        GLES30.glEnableVertexAttribArray(inColor)

        //解绑
        GLES30.glBindVertexArray(GLES30.GL_NONE)
        GLES30.glBindBuffer(GLES30.GL_ARRAY_BUFFER, GLES30.GL_NONE)
        // finish VBO bind
    }

    private fun getVertices(): FloatBuffer {
        val vertices = floatArrayOf(
            // 位置                // 颜色
            0.0f,   0.5f,  0.0f,  1.0f, 0.0f, 0.0f,   // 顶点
            -0.5f, -0.5f,  0.0f,  0.0f, 1.0f, 0.0f,   // 左
            0.5f,  -0.5f,  0.0f,  0.0f, 0.0f, 1.0f    // 右
        )

        // vertices.length*4是因为一个float占四个字节
        val vertexBuffer = ByteBuffer.allocateDirect(vertices.size * VERTICES_FLOAT_SIZE)//4个字节
            .order(ByteOrder.nativeOrder())
            .asFloatBuffer()
            .put(vertices)
            .apply {
                position(0)
            }

        return vertexBuffer
    }
}