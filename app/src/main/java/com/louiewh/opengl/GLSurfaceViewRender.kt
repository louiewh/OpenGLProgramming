package com.louiewh.opengl

import android.opengl.GLES20
import android.opengl.GLSurfaceView
import android.util.Log
import java.nio.ByteBuffer
import java.nio.ByteOrder
import java.nio.FloatBuffer
import javax.microedition.khronos.egl.EGLConfig
import javax.microedition.khronos.opengles.GL10


class GLSurfaceViewRender:GLSurfaceView.Renderer {

    private var program = 0
    private var vPosition = 0
    private var uColor = 0

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


    override fun onSurfaceCreated(gl: GL10?, config: EGLConfig?) {
        initGLES20()
    }

    override fun onSurfaceChanged(gl: GL10?, width: Int, height: Int) {
        GLES20.glViewport(0,0,width,height)
    }

    override fun onDrawFrame(gl: GL10?) {
        val vertices = getVertices()
        GLES20.glClear(GLES20.GL_DEPTH_BUFFER_BIT or GLES20.GL_COLOR_BUFFER_BIT)
        GLES20.glUseProgram(program)

        GLES20.glVertexAttribPointer(vPosition, 2, GLES20.GL_FLOAT, false, 0, vertices)
        GLES20.glEnableVertexAttribArray(vPosition)

        GLES20.glUniform4f(uColor, 0.0f, 1.0f, 0.0f, 1.0f)
        GLES20.glDrawArrays(GLES20.GL_TRIANGLE_STRIP, 0, 3)
    }


    private fun loadShader(shaderType: Int, sourceCode: String): Int {
        var shader = GLES20.glCreateShader(shaderType)
        if (shader != 0) {
            GLES20.glShaderSource(shader, sourceCode)
            GLES20.glCompileShader(shader)
            val compiled = IntArray(1)
            GLES20.glGetShaderiv(shader, GLES20.GL_COMPILE_STATUS, compiled, 0)
            if (compiled[0] == 0) {
                Log.e("ES20_ERROR", "Could not compile shader $shaderType:")
                Log.e("ES20_ERROR", GLES20.glGetShaderInfoLog(shader))
                GLES20.glDeleteShader(shader)
                shader = 0
            }
        }
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
            val linkStatus = IntArray(1)
            GLES20.glGetProgramiv(program, GLES20.GL_LINK_STATUS, linkStatus, 0)
            if (linkStatus[0] != GLES20.GL_TRUE) {
                Log.e("ES20_ERROR", "Could not link program: ")
                Log.e("ES20_ERROR", GLES20.glGetProgramInfoLog(program))
                GLES20.glDeleteProgram(program)
                program = 0
            }
        }
        return program
    }

    private fun getVertices(): FloatBuffer? {
        val vertices = floatArrayOf(
            0.0f, 0.5f,
            -0.5f, -0.5f,
            0.5f, -0.5f
        )
        // vertices.length*4是因为一个float占四个字节
        val vbb: ByteBuffer = ByteBuffer.allocateDirect(vertices.size * 4)
        vbb.order(ByteOrder.nativeOrder())
        val vertexBuf: FloatBuffer = vbb.asFloatBuffer()
        vertexBuf.put(vertices)
        vertexBuf.position(0)
        return vertexBuf
    }

    private fun initGLES20(){
        program = createProgram(verticesSource, fragmentSource)
        vPosition = GLES20.glGetAttribLocation(program, "vPosition")
        uColor = GLES20.glGetUniformLocation(program, "uColor")
        GLES20.glClearColor(1.0f, 0F, 0F, 1.0f)
    }
}