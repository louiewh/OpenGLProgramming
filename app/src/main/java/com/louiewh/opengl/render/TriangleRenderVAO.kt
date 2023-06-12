package com.louiewh.opengl.render

import com.louiewh.opengl.shader.TriangleShaderVAO

class TriangleRenderVAO: BaseRender() {

    override fun initShader() {
        shader = TriangleShaderVAO()
    }
}
