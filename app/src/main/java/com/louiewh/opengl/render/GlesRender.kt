package com.louiewh.opengl.render

import android.util.Log
import com.louiewh.opengl.GlesRenderConst
import com.louiewh.opengl.shader.ShaderStructArray
import com.louiewh.opengl.shader.Texture2DRender
import com.louiewh.opengl.shader.TriangleShader
import com.louiewh.opengl.shader.TriangleShaderVAO
import com.louiewh.opengl.shader.TriangleShaderVBO
import com.louiewh.opengl.shader.YUVRender
import com.louiewh.opengl.shader.YUVRenderLuma

class GlesRender(private val render:String): BaseRender() {
    override fun initShader() {
        Log.e("Gles", "initShader $render")
        when (render) {
            GlesRenderConst.renderArray[0] ->{
                shader = TriangleShader()
            }
            GlesRenderConst.renderArray[1] ->{
                shader = TriangleShaderVBO()
            }
            GlesRenderConst.renderArray[2] ->{
                shader = TriangleShaderVAO()
            }
            GlesRenderConst.renderArray[3] ->{
                shader = ShaderStructArray()
            }
            GlesRenderConst.renderArray[4] ->{
                shader = Texture2DRender()
            }
            GlesRenderConst.renderArray[5] ->{
                shader = YUVRender()
            }
            GlesRenderConst.renderArray[6] ->{
                shader = YUVRenderLuma()
            }

            null -> {
                shader = TriangleShader()
            }
        }
    }
}
