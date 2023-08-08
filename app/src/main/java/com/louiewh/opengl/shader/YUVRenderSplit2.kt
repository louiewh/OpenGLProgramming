package com.louiewh.opengl.shader

class YUVRenderSplit2:YUVRender() {

    override fun getFragmentSource(): String {
        return readGlslSource("YUVRenderSplit2.frag")
    }
}