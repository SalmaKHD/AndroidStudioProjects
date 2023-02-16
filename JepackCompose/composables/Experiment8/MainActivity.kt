package com.salmakhd.composecourseyoutube

import android.os.Bundle
import android.view.MotionEvent
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInteropFilter
import androidx.compose.ui.layout.boundsInWindow
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import java.lang.Math.PI
import kotlin.math.roundToInt

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color(0XFF101010))
            ) {
                Row (
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .border(1.dp, Color.Green, RoundedCornerShape(10.dp))
                        .padding(30.dp)
                ) {
                    var volume by remember {
                        mutableStateOf(0f)
                    }

                    val barCount = 20

                    MusicKnob(
                        modifier = Modifier.size(100.dp)) {
                        volume = it
                    }

                    Spacer(
                        modifier = Modifier.width(20.dp)
                    )

                    VolumeBar(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(30.dp),
                        activeBars = (barCount * volume) .roundToInt(),
                        barCount = barCount
                    )

                }
            }
        }
    }
}

@Composable
fun VolumeBar(
    modifier:Modifier= Modifier,
    activeBars: Int = 0,
    barCount: Int = 10
) {
    BoxWithConstraints(
        contentAlignment = Alignment.Center,
        modifier = modifier) {
        // calculate the width of each bar
        val barWidth = remember {
            constraints.maxWidth / (2f * barCount)
        }

        Canvas(
            modifier = modifier) {
            for (i in 0..barCount) {
                drawRoundRect(
                    color = if (i in 0..activeBars) Color.Green else Color.DarkGray,
                    topLeft = Offset(i * barWidth * 2f + barWidth /2f, 0f),
                    size = Size(barWidth, constraints.maxHeight.toFloat()),
                    cornerRadius = CornerRadius(0f)
                )
            }
        }
    }
}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun MusicKnob(
    modifier: Modifier = Modifier,
    limitingAngle: Float = 25f,
    onValueChanged: (Float) -> Unit
) {
    // create a state for the knob rotation
    var rotation by remember {
        // initial value of limitingAngle by default: 25 degrees
        mutableStateOf(limitingAngle)
    }

    // create a state for the x-coordinate of the pressed mouse position
    var touchX by remember {
        mutableStateOf(0f)
    }

    // create a state for the y-coordinate of the pressed mouse position
    var touchY by remember {
        mutableStateOf(0f)
    }

    // create a state for the x-coordinate of the current center of the knob
    var centerX by remember {
        mutableStateOf(0f)
    }

    // create a state for the x-coordinate of the current center of the knob
    var centerY by remember {
        mutableStateOf(0f)
    }

    Image(
        painter = painterResource(id = R.drawable.knob_image),
        contentDescription = "music knob",
        modifier = modifier
            .fillMaxSize()
            // will return info about image coordinates once the image is rendered
            .onGloballyPositioned {
                /*
                boundsInWindow() -> gives access to points on the image in
                relation to the entire screen
                 */
                val windowBounds = it.boundsInWindow()
                // find the center of the image
                centerX = windowBounds.size.width / 2f
                centerY = windowBounds.size.height / 2f
            }

            // behaves like the onTouch() function
            .pointerInteropFilter { event ->
                touchX = event.x
                touchY = event.y
                val angle =
                // calculate the rotation angle using the Pythagorean theorem and convert
                    // the result to degrees
                    -kotlin.math.atan2(centerX - touchX, centerY - touchY) * (180f / PI).toFloat()

                // identify the type of event
                when (event.action) {
                    MotionEvent.ACTION_DOWN,
                    MotionEvent.ACTION_MOVE ->
                        // check if the angle is in the legal range
                        if (angle !in -limitingAngle..limitingAngle) {
                            // the angle will become -180 < in the third and fourth quarters
                            // convert these degrees to positive degrees
                            val fixedAngle = if (angle in -180f..-limitingAngle) {
                                360f + angle
                            } else {
                                angle
                            }
                            rotation = fixedAngle

                            // calculate the percentage of rotation that should occur
                            // exclude the limiting angle
                            val percent = (fixedAngle - limitingAngle) / (360f - 2 * limitingAngle)

                            // execute the function passed to the composable function
                            onValueChanged(percent)

                            // operation successful, return true
                            true
                        } else false
                    else -> {
                        false
                    }
                }
            }

            // this will be called every time the value of the rotation variable
            // changes thanks to remember
            .rotate(rotation)
    )
}

