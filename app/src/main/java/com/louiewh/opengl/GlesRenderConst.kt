package com.louiewh.opengl

import com.louiewh.opengl.shader.BaseShader
import com.louiewh.opengl.shader.ShaderOrthoMatrix
import com.louiewh.opengl.shader.ShaderStructArray
import com.louiewh.opengl.shader.Texture2DRender
import com.louiewh.opengl.shader.TriangleShader
import com.louiewh.opengl.shader.TriangleShaderVAO
import com.louiewh.opengl.shader.TriangleShaderVBO
import com.louiewh.opengl.shader.YUVRender
import com.louiewh.opengl.shader.YUVRenderColorReverse
import com.louiewh.opengl.shader.YUVRenderLuma
import com.louiewh.opengl.shader.YUVRenderSplit2
import com.louiewh.opengl.shader.YUVRenderSplit4

object GlesRenderConst {

    val renderArray:Array<String> = arrayOf(
        "TriangleShader",
        "TriangleShaderVBO",
        "TriangleShaderVAO",
        "ShaderStructArray",
        "Texture2D",
        "YUVRender",
        "YUVRenderLuma",
        "YUVRenderColorReverse",
        "YUVRenderSplit",
        "YUVRenderLumaSplit2",
        "ShaderOrthoMatrix"
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
                shader = YUVRenderSplit4()
            }
            renderArray[9] ->{
                shader = YUVRenderSplit2()
            }
            renderArray[10] ->{
                shader = ShaderOrthoMatrix()
            }
        }

        return shader
    }
}