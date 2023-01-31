package com.bignerdranch.draganddraw

import android.app.Application

class DragAndDrawApplication: Application() {
    override fun onCreate() {
        super.onCreate()

        // initialize the BoxesRepository class as the data holder during
        // app execution
        BoxesRepository.initialize()
    }
}