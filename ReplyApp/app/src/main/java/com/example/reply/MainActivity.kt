/*
 * Copyright 2022 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.reply

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.example.reply.ui.ReplyApp
import com.example.reply.ui.theme.ReplyTheme

/**
 * Activity for Reply app
 */
class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            ReplyTheme {
                // calculate the current size of the window using the WindowSizeClass API
                val windowSize = calculateWindowSizeClass(this)
                ReplyApp(windowSize = windowSize.widthSizeClass)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ReplyAppPreview() {
    ReplyTheme {
        // for previewing purposes
        ReplyApp(windowSize = WindowWidthSizeClass.Compact)
    }
}

// create a preview for medium-sized screens
@Preview
@Composable
fun ReplyAppMediumPreview() {
    ReplyTheme {
        ReplyApp(windowSize = WindowWidthSizeClass.Medium)
    }
}

// create a preview for large screens
@Preview
@Composable
fun ReplyAppExpandedPreview() {
    ReplyTheme {
        ReplyApp(windowSize = WindowWidthSizeClass.Expanded)
    }
}



