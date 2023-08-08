package com.louiewh.opengl.shader

class YUVRenderSplit4:YUVRender() {

    override fun getFragmentSource(): String {
        return readGlslSource("YUVRenderSplit4.frag")
    }
}