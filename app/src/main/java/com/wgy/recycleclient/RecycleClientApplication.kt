package com.wgy.recycleclient

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context

class RecycleClientApplication: Application() {
    companion object{
        @SuppressLint("StaticFieldLeak")
        lateinit var context: Context
    }

    override fun onCreate() {
        super.onCreate()
        context = applicationContext
    }
}