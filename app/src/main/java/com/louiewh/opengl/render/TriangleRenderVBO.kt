package com.louiewh.opengl.render

import com.louiewh.opengl.shader.TriangleShaderVBO


open class TriangleRenderVBO: BaseRender() {

    override fun initShader() {
        shader = TriangleShaderVBO()
    }
}
