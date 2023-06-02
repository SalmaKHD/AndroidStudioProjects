package com.salmakhd.android.forpractice

import android.Manifest
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.ui.Modifier
import androidx.core.app.ActivityCompat
import androidx.lifecycle.LifecycleObserver
import com.salmakhd.android.forpractice.FusedLocationProvider.LocationService

class MainActivity : ComponentActivity(), LifecycleObserver {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        ActivityCompat.requestPermissions(
            this,
            arrayOf(
                Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.ACCESS_COARSE_LOCATION
            ), 0
        )

        setContent {
            Column(modifier = Modifier) {
                Button(
                    onClick = {
                        Intent(applicationContext, LocationService::class.java).apply {
                            action = LocationService.ACTION_START
                            startService(this)
                        }
                    }
                ) {
                    Text(
                        text = "Start"
                    )
                }
                Button(
                    onClick = {
                        Intent(applicationContext, LocationService::class.java).apply {
                            action = LocationService.ACTION_STOP
                            startService(this)
                        }

                    }
                ) {
                    Text(
                        text = "Stop"
                    )
                }
            }
        }
    }
}
