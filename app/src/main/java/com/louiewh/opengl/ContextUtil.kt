package com.louiewh.opengl

import android.content.Context
import java.lang.ref.WeakReference

object ContextUtil {
    private lateinit var mContext:WeakReference<Context>

    fun init(context: Context){
        mContext = WeakReference<Context>(context)
    }

    fun getContext():Context{
        return mContext.get()!!
    }
}