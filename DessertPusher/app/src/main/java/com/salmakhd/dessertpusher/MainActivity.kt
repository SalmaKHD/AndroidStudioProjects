package com.salmakhd.dessertpusher

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import timber.log.Timber

private const val TAG = "MainActivity"
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Log.i(TAG, "onCreate() callback called.")
        // alternative way
        Timber.i("onCreate() callback called (Timber-generated).")
    }

    override fun onStart() {
        super.onStart()
        Log.i(TAG, "onStart() callback called")
        Timber.i("onStart() callback called (Timber generated).")
    }

    override fun onResume() {
        super.onResume()
        Log.i(TAG, "onResume() callback called")
        Timber.i("onResume() callback called (Timber generated).")
    }

    override fun onPause() {
        super.onPause()
        Log.i(TAG, "onPause() callback called")
        Timber.i("onPause() callback called (Timber generated).")
    }

    override fun onStop() {
        super.onStop()
        Log.i(TAG, "onStop() callback called")
        Timber.i("onStop() callback called (Timber generated).")
    }
    override fun onRestart() {
        super.onRestart()
        Log.i(TAG, "onRestart() callback called")
        Timber.i("onRestart() callback called (Timber generated).")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.i(TAG, "onDestroy() callback called")
        Timber.i("onDestroy() callback called (Timber generated).")
    }
}

