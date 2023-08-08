package com.louiewh.opengl

import android.content.Context
import android.util.Log
import java.io.BufferedInputStream

/**
 * author: louiewang
 * date: 2023/8/8 12:23
 * description:
 */
object GlslAssetsUtil {

    /**
     * 读取assets中的【文本文件】内容
     */
    fun readGlslSource(ctx: Context, fileName: String):String {
        var text: String? = null
        try {
            ctx.assets.open(fileName).use { ins ->
                BufferedInputStream(ins).use { bis ->
                    text = bis.reader().readText()
                }
            }
        } catch (e:Exception){
            Log.e("AssetsUtil", "readText4Assets", e)
        }
        return text!!.trim()
    }
}