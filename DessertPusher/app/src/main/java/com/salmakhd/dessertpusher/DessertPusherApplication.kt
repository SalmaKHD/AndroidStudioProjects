package com.salmakhd.dessertpusher

import android.app.Application
import timber.log.Timber

class DessertPusherApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        // instantiate the Timber class
        // Timber is a logging library that eliminates the need for creating tags
        Timber.plant(Timber.DebugTree())
    }
}