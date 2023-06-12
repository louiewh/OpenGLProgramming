package com.louiewh.opengl

import android.opengl.GLES20
import java.nio.ByteBuffer
import java.nio.ByteOrder
import java.nio.FloatBuffer
import javax.microedition.khronos.opengles.GL10

open class TriangleShader: BaseShader() {

    protected var vPosition = 0
    protected var uColor = 0

    private  val verticesSource =
        "attribute vec2 vPosition;            \n" +
                "void main(){                         \n" +
                "gl_Position = vec4(vPosition,0,1);\n" +
                "}"

    private  val fragmentSource =
        "precision mediump float;         \n" +
                "uniform vec4 uColor;             \n" +
                "void main(){                     \n" +
                "gl_FragColor = uColor;        \n"    +
                "}"

    override fun onInitGLES(program: Int) {
        vPosition = GLES20.glGetAttribLocation(program, "vPosition")
        uColor = GLES20.glGetUniformLocation(program, "uColor")
    }

    override fun getVerticesSource(): String {
        return verticesSource
    }

    override fun getFragmentSource(): String {
        return fragmentSource
    }

    open fun onDrawFrame(gl: GL10?) {
        val vertices = getVertices()
        GLES20.glClear(GLES20.GL_DEPTH_BUFFER_BIT or GLES20.GL_COLOR_BUFFER_BIT)
        GLES20.glUseProgram(getShaderProgram())

        GLES20.glVertexAttribPointer(vPosition, 2, GLES20.GL_FLOAT, false, 8, vertices)
        GLES20.glEnableVertexAttribArray(vPosition)

        GLES20.glUniform4f(uColor, 0.0f, 1.0f, 0.0f, 1.0f)
        GLES20.glDrawArrays(GLES20.GL_TRIANGLE_STRIP, 0, 3)
    }

    fun getVertices(): FloatBuffer {
        val vertices = floatArrayOf(
            0.0f, 0.5f,
            -0.5f, -0.5f,
            0.5f, -0.5f
        )

        // vertices.length*4是因为一个float占四个字节
        val vertexBuffer = ByteBuffer.allocateDirect(vertices.size * 4)//4个字节
            .order(ByteOrder.nativeOrder())
            .asFloatBuffer()
            .put(vertices)
            .apply {
                position(0)
            }

        return vertexBuffer
    }
}