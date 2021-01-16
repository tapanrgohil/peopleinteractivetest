package com.shaadi

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class ShaadiApp :Application(){
    companion object {
        lateinit var INSTANCE: ShaadiApp
    }

    override fun onCreate() {
        super.onCreate()
        INSTANCE = this
    }
}