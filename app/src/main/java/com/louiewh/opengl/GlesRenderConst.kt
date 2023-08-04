package com.louiewh.opengl

import com.louiewh.opengl.shader.BaseShader
import com.louiewh.opengl.shader.ShaderOrthoMatrix
import com.louiewh.opengl.shader.ShaderRotateMatrix
import com.louiewh.opengl.shader.ShaderScaleMatrix
import com.louiewh.opengl.shader.ShaderStructArray
import com.louiewh.opengl.shader.ShaderTranslateMatrix
import com.louiewh.opengl.shader.Texture2DRender
import com.louiewh.opengl.shader.Texture3DClubRender
import com.louiewh.opengl.shader.Texture3DMutiClubRender
import com.louiewh.opengl.shader.Texture3DRender
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
        "ShaderOrthoMatrix",
        "ShaderScaleMatrix",
        "ShaderTranslateMatrix",
        "ShaderRotateMatrix",
        "Texture3DRender",
        "Texture3DClubRender",
        "Texture3DMutiClubRender",
        "GlesSurfaceView",
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
            renderArray[11] ->{
                shader = ShaderScaleMatrix()
            }
            renderArray[12] ->{
                shader = ShaderTranslateMatrix()
            }
            renderArray[13] ->{
                shader = ShaderRotateMatrix()
            }
            renderArray[14] ->{
                shader = Texture3DRender()
            }
            renderArray[15] ->{
                shader = Texture3DClubRender()
            }
            renderArray[16] ->{
                shader = Texture3DMutiClubRender()
            }
            "GlesSurfaceView" ->{
                shader = ShaderStructArray()
            }
        }

        return shader
    }
}