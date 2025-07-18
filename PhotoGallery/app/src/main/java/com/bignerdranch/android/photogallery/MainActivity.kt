package com.bignerdranch.android.photogallery

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    companion object {
        // this function will be used to launch the app programmatically
        fun newIntent(context: Context): Intent {
            return Intent(context, MainActivity::class.java)
        }
    }

}