package com.salmakhd.android.forpractice

import android.app.*
import android.content.Intent
import android.media.MediaPlayer
import android.os.Build
import android.os.IBinder
import androidx.annotation.RequiresApi
import com.salmakhd.android.forpractice.Constants.CHANNEL_ID

class MyService: Service() {
    private lateinit var musicPlayer: MediaPlayer
    override fun onBind(p0: Intent?): IBinder? {
        return null
    }

    override fun onCreate() {
        super.onCreate()
        initMusic()
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        showNotification()
        if (musicPlayer.isPlaying) musicPlayer.stop()
        else musicPlayer.start()
        return START_STICKY
    }
    private fun createNotificationChannel() {
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val serviceChannel = NotificationChannel(
                CHANNEL_ID, "My service channel",
                    NotificationManager.IMPORTANCE_DEFAULT
            )

            val manager = getSystemService(
                NotificationManager::class.java
            )
        }

    }
    private fun initMusic() {
        musicPlayer = MediaPlayer.create(this, R.raw.audio)
        musicPlayer.isLooping = true
        musicPlayer.setVolume(0f, 0f)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun showNotification() {
        val notificationIntent = Intent(this, MainActivity::class.java)
        val pendingIntent = PendingIntent.getActivity(this, 0, notificationIntent, 0)

        val notification  = Notification
            .Builder(this, CHANNEL_ID)
            .setContentText("Music player")
            .setContentIntent(pendingIntent)
            .build()
    }
}