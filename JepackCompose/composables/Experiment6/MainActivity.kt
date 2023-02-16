package com.salmakhd.composecourseyoutube

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.animateColor
import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            // define a state for the box size
            var sizeState by remember { mutableStateOf(200.dp) }

            // define an animator for increasing the size of the box
            val size by animateDpAsState(
                targetValue = sizeState,
                // use spring animation
                spring(
                    Spring.DampingRatioHighBouncy
                )
            )

            // define an infinite animation
            val infiniteTransition = rememberInfiniteTransition()
            val color by infiniteTransition.animateColor(
                initialValue = Color.Red,
                targetValue = Color.Green,
                animationSpec = infiniteRepeatable(
                    tween(
                        durationMillis = 2000
                    ),
                    repeatMode = RepeatMode.Reverse
                )
            )

            Box(
                modifier= Modifier
                    // use the new value of size (increased gradually)
                    .size(size)
                    .background(color),
                contentAlignment = Alignment.Center
            ) {
                Button(
                    onClick = {
                        sizeState += 50.dp
                    }
                ) {
                    Text(text = "Increase Size")
                }
            }
        }
    }
}

