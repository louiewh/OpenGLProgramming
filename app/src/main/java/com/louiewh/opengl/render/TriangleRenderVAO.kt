package com.louiewh.opengl.render

import com.louiewh.opengl.shader.ShaderStructArray

class TriangleRenderVAO: BaseRender() {

    override fun initShader() {
        shader = ShaderStructArray()
    }
}
