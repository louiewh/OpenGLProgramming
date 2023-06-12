package com.louiewh.opengl

import android.opengl.GLES20
import java.nio.IntBuffer
import javax.microedition.khronos.opengles.GL10

class TriangleShaderVBO: TriangleShader() {

    private var VBO = 0

    override fun onInitGLES(program: Int) {
        super.onInitGLES(program)
        initVBO()
    }

    override fun onDrawFrame(gl: GL10?) {
        val vertices = getVertices()
        GLES20.glClear(GLES20.GL_DEPTH_BUFFER_BIT or GLES20.GL_COLOR_BUFFER_BIT)
        GLES20.glUseProgram(getShaderProgram())

        GLES20.glVertexAttribPointer(vPosition, 2, GLES20.GL_FLOAT, false, 8, vertices)
        GLES20.glEnableVertexAttribArray(vPosition)

        GLES20.glUniform4f(uColor, 0.0f, 1.0f, 0.0f, 1.0f)
        GLES20.glDrawArrays(GLES20.GL_TRIANGLE_STRIP, 0, 3)

//        GLES20.glClear(GLES20.GL_DEPTH_BUFFER_BIT or GLES20.GL_COLOR_BUFFER_BIT)
//        GLES20.glUseProgram(program)
//
//        GLES20.glBindBuffer(GLES20.GL_ARRAY_BUFFER, VBO)
//        GLES20.glEnableVertexAttribArray(vPosition)
//        GLES20.glVertexAttribPointer(vPosition, 2, GLES20.GL_FLOAT, false, 2*4, 0);
//
//        GLES20.glUniform4f(uColor, 0.0f, 1.0f, 0.0f, 1.0f)
//        GLES20.glDrawArrays(GLES20.GL_TRIANGLE_STRIP, 0, 3)
//
//        GLES20.glBindBuffer(GLES20.GL_ARRAY_BUFFER, GLES20.GL_NONE)
    }

    private fun initVBO() {
        val vertices = getVertices()
        val vboBuffer = IntBuffer.allocate(1)
        GLES20.glGenBuffers(1, vboBuffer)
        VBO = vboBuffer.get()
        GLES20.glBindBuffer(GLES20.GL_ARRAY_BUFFER, VBO)
        GLES20.glBufferData(
            GLES20.GL_ARRAY_BUFFER, vertices.capacity(), vertices,
            GLES20.GL_STREAM_DRAW
        )
        GLES20.glBindBuffer(GLES20.GL_ARRAY_BUFFER, 0)
    }
}