package com.louiewh.opengl

import android.opengl.GLES20
import android.opengl.GLSurfaceView
import javax.microedition.khronos.egl.EGLConfig
import javax.microedition.khronos.opengles.GL10


class TriangleRender:GLSurfaceView.Renderer {

    private val triangleShader: TriangleShader = TriangleShaderVBO()

    open fun setGLSurfaceView(glSurfaceView:GLSurfaceView){
        // 设置 OpenGL 版本(一定要设置)
        glSurfaceView.setEGLContextClientVersion(2)
        // 设置渲染器(后面会讲，可以理解成画笔)
        glSurfaceView.setRenderer(this)
        // 设置渲染模式为连续模式(会以 60 fps 的速度刷新)
        glSurfaceView.renderMode = GLSurfaceView.RENDERMODE_CONTINUOUSLY
    }

    override fun onSurfaceCreated(gl: GL10?, config: EGLConfig?) {
        triangleShader.initGLES20()
    }

    override fun onSurfaceChanged(gl: GL10?, width: Int, height: Int) {
        GLES20.glViewport(0, 0, width, height)
    }

    override fun onDrawFrame(gl: GL10?) {
        triangleShader.onDrawFrame(gl)
    }
}
