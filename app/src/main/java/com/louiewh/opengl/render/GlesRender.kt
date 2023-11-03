package com.louiewh.opengl.render

import android.util.Log
import com.louiewh.opengl.GlesConst

class GlesRender(private val renderName:String): BaseRender() {
    override fun initShader() {
        Log.e("Gles", "initShader $renderName")

        baseShader = GlesConst.getShader(renderName)
    }
}
