package com.louiewh.opengl.render

import android.opengl.GLSurfaceView
import com.louiewh.opengl.GlesSurfaceView
import com.louiewh.opengl.shader.BaseShader
import javax.microedition.khronos.egl.EGLConfig
import javax.microedition.khronos.opengles.GL10

abstract class BaseRender:GLSurfaceView.Renderer  {

    protected var baseShader:BaseShader? = null

    abstract fun initShader()

    open fun setGLSurfaceView(glSurfaceView:GLSurfaceView){
        // 设置 OpenGL 版本(一定要设置)
        glSurfaceView.setEGLContextClientVersion(3)
        // 设置渲染器(后面会讲，可以理解成画笔)
        glSurfaceView.setRenderer(this)
        // 设置渲染模式为连续模式(会以 60 fps 的速度刷新)

        glSurfaceView.renderMode = GLSurfaceView.RENDERMODE_CONTINUOUSLY
        baseShader?.onSetGLSurfaceView(glSurfaceView)
    }

    fun setGlesSurfaceView(glSurfaceView:GlesSurfaceView){
        // 设置 OpenGL 版本(一定要设置)
        // glSurfaceView.setEGLContextClientVersion(3)
        // 设置渲染器(后面会讲，可以理解成画笔)
        glSurfaceView.setRenderer(this)
        // 设置渲染模式为连续模式(会以 60 fps 的速度刷新)

        glSurfaceView.setRenderMode(GLSurfaceView.RENDERMODE_CONTINUOUSLY)
        // baseShader?.onSetGLSurfaceView(glSurfaceView)
    }

    override fun onSurfaceCreated(gl: GL10?, config: EGLConfig?) {
        baseShader?.onSurfaceCreated()
    }

    override fun onSurfaceChanged(gl: GL10?, width: Int, height: Int) {
        baseShader?.onSurfaceChanged(gl, width, height)
    }

    override fun onDrawFrame(gl: GL10?) {
        baseShader?.onDrawFrame(gl)
    }

    fun destroyShader(){
        baseShader?.destroyGLES()
    }
}