package com.louiewh.opengl.shader

import android.opengl.GLES30
import java.nio.ByteBuffer
import java.nio.ByteOrder
import java.nio.FloatBuffer
import javax.microedition.khronos.opengles.GL10

open class TriangleShader: BaseShader() {

    protected var vPosition = 0
    protected var uColor = 0

    override fun onInitGLES(program: Int) {
        vPosition = GLES30.glGetAttribLocation(program, "vPosition")
        uColor = GLES30.glGetUniformLocation(program, "uColor")
    }

    override fun onDestroyGLES() {

    }

    override fun getVertexSource(): String {
        return readGlslSource("TriangleShader.vert")
    }

    override fun getFragmentSource(): String {
        return readGlslSource( "TriangleShader.frag")
    }

    override fun onDrawFrame(gl: GL10?) {
        val vertices = getVertices()
        GLES30.glClear(GLES30.GL_DEPTH_BUFFER_BIT or GLES30.GL_COLOR_BUFFER_BIT)
        GLES30.glUseProgram(getShaderProgram())

        GLES30.glVertexAttribPointer(vPosition, 2, GLES30.GL_FLOAT, false, 8, vertices)
        GLES30.glEnableVertexAttribArray(vPosition)

        GLES30.glUniform4f(uColor, 0.0f, 1.0f, 0.0f, 1.0f)
        GLES30.glDrawArrays(GLES30.GL_TRIANGLE_STRIP, 0, 3)
    }

    open fun getVertices(): FloatBuffer {
        val vertices = floatArrayOf(
            0.0f, 0.5f,
            -0.5f, -0.5f,
            0.5f, -0.5f
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