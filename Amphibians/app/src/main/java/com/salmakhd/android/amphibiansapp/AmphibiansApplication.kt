package com.salmakhd.android.amphibiansapp

import android.app.Application
import com.salmakhd.android.amphibiansapp.data.AppContainer
import com.salmakhd.android.amphibiansapp.data.DefaultAppContainer

class AmphibiansApplication: Application() {
    lateinit var container: AppContainer
    override fun onCreate() {
        super.onCreate()
        container = DefaultAppContainer()
    }
}