package com.compdfkit.conversion.utils

import android.content.Context

object AppContextHolder {
    private lateinit var appContext: Context

    fun init(context: Context) {
        appContext = context.applicationContext
    }

    fun get(): Context = appContext
}