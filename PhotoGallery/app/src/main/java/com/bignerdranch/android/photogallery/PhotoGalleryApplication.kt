package com.bignerdranch.android.photogallery

import android.app.Application
import android.app.NotificationChannel
import android.app.NotificationManager
import android.os.Build

const val NOTIFICATION_CHANNEL_ID = "flickr_poll"
// this class extends the Application class which ensures all the objects
// created inside it will persist till the app process is killed.
class PhotoGalleryApplication: Application() {
    override fun onCreate() {
        super.onCreate()
        PreferencesRepository.initialize(this)

        // create a channel for notifications: API >= 26 needed
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val name = getString(R.string.notification_channel_name)

            // assign priority to the channel
            val importance = NotificationManager.IMPORTANCE_DEFAULT

            // create a channel for showing a min of one category of notifications
            val channel =
                NotificationChannel(NOTIFICATION_CHANNEL_ID, name, importance)

            // get NotificationManager object for creating a channel
            val notificationManager: NotificationManager =
                getSystemService(NotificationManager::class.java)

            // create channel
            notificationManager.createNotificationChannel(channel)

        }
    }
}