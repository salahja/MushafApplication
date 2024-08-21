package com.example.mushafconsolidatedimport

import android.app.Application
import android.content.Context
import androidx.appcompat.app.AppCompatDelegate


class MushafApplication : Application() {
    private fun setAppContext(mAppContext: Context) {
        MushafApplication.appContext = mAppContext
    }

    override fun onCreate() {
        super.onCreate()
        MushafApplication.instance = this
        setAppContext(applicationContext)
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true)
    }

    companion object {
        var instance: MushafApplication? = null
            get() {
                return MushafApplication.instance
            }
        var appContext: Context? = null
            get() {
                return MushafApplication.appContext
            }

        init {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
        }
    }
}