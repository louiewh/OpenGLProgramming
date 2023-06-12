package com.louiewh.opengl.render

import com.louiewh.opengl.shader.TriangleShader


open class TriangleRender: BaseRender() {

    override fun initShader() {
        shader = TriangleShader()
    }
}
