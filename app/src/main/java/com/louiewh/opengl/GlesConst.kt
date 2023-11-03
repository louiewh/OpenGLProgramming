package com.louiewh.opengl

import android.os.Bundle
import androidx.annotation.IdRes
import androidx.navigation.NavController
import com.louiewh.opengl.shader.BaseShader
import com.louiewh.opengl.shader.ShaderOrthoMatrix
import com.louiewh.opengl.shader.ShaderRotateMatrix
import com.louiewh.opengl.shader.ShaderScaleMatrix
import com.louiewh.opengl.shader.ShaderStructArray
import com.louiewh.opengl.shader.Texture2DShader
import com.louiewh.opengl.shader.Texture3DClubShader
import com.louiewh.opengl.shader.Texture3DMutiClubShader
import com.louiewh.opengl.shader.Texture3DShader
import com.louiewh.opengl.shader.TriangleShader
import com.louiewh.opengl.shader.TriangleShaderVAO
import com.louiewh.opengl.shader.TriangleShaderVBO
import com.louiewh.opengl.shader.YUVRender
import com.louiewh.opengl.shader.YUVRenderColorReverse
import com.louiewh.opengl.shader.YUVRenderLuma
import com.louiewh.opengl.shader.YUVRenderSplit2
import com.louiewh.opengl.shader.YUVRenderSplit4

object GlesConst {

    data class ShaderMeta(
        val renderName: String,
        val shader:BaseShader,
        @IdRes val naviID:Int
    )

    val shaderArray:Array<ShaderMeta> = arrayOf(
        ShaderMeta("TriangleShader",          TriangleShader(),          R.id.action_FragmentShaderList_to_FragmentTriangle),
        ShaderMeta("TriangleShaderVBO",       TriangleShaderVBO(),       R.id.action_FragmentShaderList_to_FragmentTriangleVBO),
        ShaderMeta("TriangleShaderVAO",       TriangleShaderVAO(),       R.id.action_FragmentShaderList_to_FragmentTriangleVAO),
        ShaderMeta("ShaderStructArray",       ShaderStructArray(),       R.id.action_FragmentShaderList_to_FragmentStructArray),
        ShaderMeta("Texture2D",               Texture2DShader(),         R.id.action_FragmentShaderList_to_FragmentTexture2D),
        ShaderMeta("YUVRender",               YUVRender(),               R.id.action_FragmentShaderList_to_FragmentTexture2D),

        ShaderMeta("YUVRenderLuma",           YUVRenderLuma(),           R.id.action_FragmentShaderList_to_FragmentYUVLuma),
        ShaderMeta("YUVRenderColorReverse",   YUVRenderColorReverse(),   R.id.action_FragmentShaderList_to_FragmentYUVColorReverse),
        ShaderMeta("YUVRenderSplit",          YUVRenderSplit4(),         R.id.action_FragmentShaderList_to_FragmentYUVSplit4),
        ShaderMeta("YUVRenderLumaSplit2",     YUVRenderSplit2(),         R.id.action_FragmentShaderList_to_FragmentYUVSplit2),

        ShaderMeta("ShaderOrthoMatrix",       ShaderOrthoMatrix(),       R.id.action_FragmentShaderList_to_FragmentMatrixOrtho),
        ShaderMeta("ShaderScaleMatrix",       ShaderScaleMatrix(),       R.id.action_FragmentShaderList_to_FragmentMatrixScale),
        ShaderMeta("ShaderTranslateMatrix",   ShaderRotateMatrix(),      R.id.action_FragmentShaderList_to_FragmentMatrixTranslate),
        ShaderMeta("ShaderRotateMatrix",      ShaderRotateMatrix(),      R.id.action_FragmentShaderList_to_FragmentMatrixRotate),

        ShaderMeta("Texture3DShader",         Texture3DShader(),         R.id.action_FragmentShaderList_to_FragmentTexture3D),
        ShaderMeta("Texture3DClubShader",     Texture3DClubShader(),     R.id.action_FragmentShaderList_to_FragmentTexture3DClub),
        ShaderMeta("Texture3DMutiClubShader", Texture3DMutiClubShader(), R.id.action_FragmentShaderList_to_FragmentTexture3DMutiClub),
        ShaderMeta("GlesSurfaceView",         ShaderStructArray(),       R.id.action_FragmentShaderList_to_FragmentGles),
   )

    fun getShader(renderName:String):BaseShader{
        return findRenderMeta(renderName)?.shader ?: TriangleShader()
    }

    fun navigation(meta: ShaderMeta, navController:NavController) {
        val bundle = Bundle()
        bundle.putString("RenderName", meta.renderName)
        navController.navigate(meta.naviID, bundle)
    }

    private fun findRenderMeta(renderName:String): ShaderMeta? {
        return shaderArray.find { it.renderName == renderName }
    }

}