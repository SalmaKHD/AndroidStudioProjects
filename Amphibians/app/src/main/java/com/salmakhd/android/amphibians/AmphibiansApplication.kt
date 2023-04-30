package com.salmakhd.android.amphibians

import android.app.Application
import com.salmakhd.android.amphibians.data.AppContainer
import com.salmakhd.android.amphibians.data.DefaultAppContainer

class AmphibiansApplication: Application() {
    lateinit var container: AppContainer
    override fun onCreate() {
        super.onCreate()
        container = DefaultAppContainer()
    }
}