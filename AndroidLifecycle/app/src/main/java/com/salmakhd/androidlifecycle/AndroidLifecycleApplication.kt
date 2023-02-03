package com.salmakhd.androidlifecycle

import android.app.Application
import timber.log.Timber

class AndroidLifecycleApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        // instantiate the Timber class
        // Timber is a logging library that eliminates the need for creating tags
        Timber.plant(Timber.DebugTree())
    }
}