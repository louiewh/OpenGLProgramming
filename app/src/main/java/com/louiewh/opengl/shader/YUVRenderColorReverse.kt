package com.louiewh.opengl.shader

class YUVRenderColorReverse:YUVRender() {

    override fun getFragmentSource(): String {
        return readGlslSource("YUVRenderColorReverse.frag")
    }
}