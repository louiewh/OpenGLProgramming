package com.louiewh.opengl

import com.louiewh.opengl.shader.BaseShader
import com.louiewh.opengl.shader.ShaderStructArray
import com.louiewh.opengl.shader.Texture2DRender
import com.louiewh.opengl.shader.TriangleShader
import com.louiewh.opengl.shader.TriangleShaderVAO
import com.louiewh.opengl.shader.TriangleShaderVBO
import com.louiewh.opengl.shader.YUVRender
import com.louiewh.opengl.shader.YUVRenderColorReverse
import com.louiewh.opengl.shader.YUVRenderLuma

object GlesRenderConst {

    val renderArray:Array<String> = arrayOf(
        "TriangleShader",
        "TriangleShaderVBO",
        "TriangleShaderVAO",
        "ShaderStructArray",
        "Texture2D",
        "YUVRender",
        "YUVRenderLuma",
        "YUVRenderColorReverse"
    )

    fun getRender(renderName:String):BaseShader{
        var shader:BaseShader = TriangleShader()

        when (renderName) {
            renderArray[0] ->{
                shader = TriangleShader()
            }
            renderArray[1] ->{
                shader = TriangleShaderVBO()
            }
            renderArray[2] ->{
                shader = TriangleShaderVAO()
            }
            renderArray[3] ->{
                shader = ShaderStructArray()
            }
            renderArray[4] ->{
                shader = Texture2DRender()
            }
            renderArray[5] ->{
                shader = YUVRender()
            }
            renderArray[6] ->{
                shader = YUVRenderLuma()
            }
            renderArray[7] ->{
                shader = YUVRenderColorReverse()
            }
            renderArray[8] ->{

            }
        }

        return shader
    }
}