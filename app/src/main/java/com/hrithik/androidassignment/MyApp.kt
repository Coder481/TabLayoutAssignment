package com.hrithik.androidassignment

import android.app.Application
import android.content.Context
import androidx.appcompat.app.AppCompatDelegate

/**
 * Application class
 */
class MyApp : Application() {
    override fun onCreate() {
        super.onCreate()
        instance = this
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true)
    }

    companion object {
        lateinit var instance: MyApp
        fun getContext(): Context = instance.applicationContext
    }
}