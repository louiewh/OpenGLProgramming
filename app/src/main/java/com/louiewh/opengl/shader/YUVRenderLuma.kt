package com.louiewh.opengl.shader

class YUVRenderLuma:YUVRender() {

    override fun getFragmentSource(): String {
        return readGlslSource("YUVRenderLuma.frag")
    }
}