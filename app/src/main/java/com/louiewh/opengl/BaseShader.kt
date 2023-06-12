package com.louiewh.opengl

import android.opengl.GLES20
import android.util.Log

abstract class BaseShader {
    private companion object {
        val TAG:String = "ES20_BaseShader"
    }

    private var mProgram:Int = 0

    abstract fun onInitGLES(program: Int)

    abstract fun getVerticesSource():String

    abstract fun getFragmentSource():String


    fun initGLES20(){
        GLES20.glClearColor(1.0f, 0F, 0F, 1.0f)

        mProgram = createProgram(getVerticesSource(), getFragmentSource())
        onInitGLES(mProgram)
    }

    protected open fun getShaderProgram():Int{
        return mProgram
    }

    private fun loadShader(shaderType: Int, sourceCode: String): Int {
        var shader = GLES20.glCreateShader(shaderType)
        if (shader != 0) {
            GLES20.glShaderSource(shader, sourceCode)
            GLES20.glCompileShader(shader)
        }

        shader = checkGetShaderiv(shader, shaderType)
        return shader
    }

    private fun createProgram(vertexSource: String, fragmentSource: String): Int {
        val vertexShader = loadShader(GLES20.GL_VERTEX_SHADER, vertexSource)
        if (vertexShader == 0) {
            return 0
        }
        val pixelShader = loadShader(GLES20.GL_FRAGMENT_SHADER, fragmentSource)
        if (pixelShader == 0) {
            return  0
        }
        var program = GLES20.glCreateProgram()
        if (program != 0) {
            GLES20.glAttachShader(program, vertexShader)
            GLES20.glAttachShader(program, pixelShader)
            GLES20.glLinkProgram(program)
        }

        program = checkGetProgramiv(program)
        return program
    }

    private fun checkGetShaderiv(shader: Int, shaderType: Int): Int {
        val compiled = IntArray(1)
        GLES20.glGetShaderiv(shader, GLES20.GL_COMPILE_STATUS, compiled, 0)
        if (compiled[0] == 0) {
            Log.e(TAG, "Could not compile shader $shaderType:")
            Log.e(TAG, GLES20.glGetShaderInfoLog(shader))
            GLES20.glDeleteShader(shader)
            return 0
        }

        return shader
    }

    private fun checkGetProgramiv(program: Int): Int {
        val linkStatus = IntArray(1)
        GLES20.glGetProgramiv(program, GLES20.GL_LINK_STATUS, linkStatus, 0)
        if (linkStatus[0] != GLES20.GL_TRUE) {
            Log.e(TAG, "Could not link program: ")
            Log.e(TAG, GLES20.glGetProgramInfoLog(program))
            GLES20.glDeleteProgram(program)

            return 0
        }
        return program
    }
}