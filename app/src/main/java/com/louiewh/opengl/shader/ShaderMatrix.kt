package com.louiewh.opengl.shader

import android.opengl.GLES30


open class ShaderMatrix: Texture2DRender() {

    protected var uMatrix = 0

    override fun getVertexSource(): String {
        return readGlslSource("ShaderMatrix.vert")
    }

    override fun getFragmentSource(): String {
        return readGlslSource("ShaderMatrix.frag")
    }

    override fun onInitGLES(program: Int) {
        super.onInitGLES(program)

        uMatrix   = GLES30.glGetUniformLocation(program, "uMatrix")
    }
}