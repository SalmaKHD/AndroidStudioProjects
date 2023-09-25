package com.salmakhd.android.amphibiansapp

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.lifecycleScope
import com.salmakhd.android.amphibiansapp.ui.AmphibiansScreen
import com.salmakhd.android.amphibiansapp.ui.theme.AmphibiansAppTheme
import kotlinx.coroutines.CoroutineName
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            AmphibiansAppTheme {
                AmphibianApp()
            }
        }
    }
}