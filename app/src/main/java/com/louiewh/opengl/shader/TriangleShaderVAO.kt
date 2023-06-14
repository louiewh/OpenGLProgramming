package com.louiewh.opengl.shader

import android.opengl.GLES20
import android.opengl.GLES30
import android.opengl.GLES30.GL_NONE
import android.util.Log
import javax.microedition.khronos.opengles.GL10
import kotlin.math.sin

class TriangleShaderVAO: TriangleShader() {

    private var VBO = 0
    private var VAO = 0

    private var mGreenValue = 0f
    private var mCurrentTime = System.currentTimeMillis()

    override fun onInitGLES(program: Int) {
        super.onInitGLES(program)
        initVBO()
        initVAO()
    }


    override fun onDrawFrame(gl: GL10?) {
        GLES30.glClear(GLES30.GL_DEPTH_BUFFER_BIT or GLES30.GL_COLOR_BUFFER_BIT)
        GLES30.glUseProgram(getShaderProgram())

        val timeValue: Long = System.currentTimeMillis()
        if(timeValue - mCurrentTime > 100) {
            mCurrentTime = timeValue
            mGreenValue = (sin((timeValue).toDouble() / 2) + 0.5f).toFloat()
            GLES20.glUniform4f(uColor, 0.0f, mGreenValue, 0.0f, 1.0f)
            Log.e("GLES", "greenVale: $mGreenValue")
        }

        GLES30.glBindVertexArray(VAO)
        GLES30.glDrawArrays(GLES30.GL_TRIANGLE_STRIP, 0, 3)

        GLES30.glBindVertexArray(GL_NONE)
    }

    private fun initVBO() {
        val vertices = getVertices()
//        val vboBuffer = IntBuffer.allocate(1)
//        GLES30.glGenBuffers(1, vboBuffer)
//        VBO = vboBuffer.get()


        val intArray = IntArray(2)
        GLES30.glGenBuffers(1, intArray, 0)
        GLES30.glGenVertexArrays(1, intArray, 1)
        VBO = intArray[0]
        VAO = intArray[1]

        //绑定VAO对象, VBO 的操作记录在VAO中
        GLES30.glBindVertexArray(VAO)

        GLES30.glBindBuffer(GLES30.GL_ARRAY_BUFFER, VBO)
        GLES30.glBufferData(
            GLES30.GL_ARRAY_BUFFER,
            vertices.capacity() * VERTICES_FLOAT_SIZE,
            vertices,
            GLES30.GL_STREAM_DRAW
        )
        GLES30.glBindBuffer(GLES30.GL_ARRAY_BUFFER, GL_NONE)

        GLES30.glBindVertexArray(GL_NONE)
    }

    private fun initVAO() {
        val intArray = IntArray(1)
        GLES30.glGenVertexArrays(1, intArray, 0)
        VAO = intArray[0]

        //绑定VAO对象, VBO 的操作记录在VAO中
        GLES30.glBindVertexArray(VAO)

        // VBO bind
        GLES20.glBindBuffer(GLES20.GL_ARRAY_BUFFER, VBO)
        GLES20.glVertexAttribPointer(vPosition, 2, GLES20.GL_FLOAT, false, 2 * 4, 0);
        GLES20.glEnableVertexAttribArray(vPosition)

        GLES20.glDrawArrays(GLES20.GL_TRIANGLE_STRIP, 0, 3)
        GLES20.glBindBuffer(GLES20.GL_ARRAY_BUFFER, GLES20.GL_NONE)
        // finish VBO bind

        //解绑
        GLES30.glBindVertexArray(GL_NONE)
    }
}