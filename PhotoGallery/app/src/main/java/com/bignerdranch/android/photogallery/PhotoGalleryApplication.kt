package com.bignerdranch.android.photogallery

import android.app.Application

// this class extends the Application class which ensures all the objects
// created inside it will persist till the app process is killed.
class PhotoGalleryApplication: Application() {
    override fun onCreate() {
        super.onCreate()
        PreferencesRepository.initialize(this)
    }
}