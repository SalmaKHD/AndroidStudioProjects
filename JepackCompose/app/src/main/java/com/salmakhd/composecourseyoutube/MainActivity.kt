package com.salmakhd.composecourseyoutube

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PointMode
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.delay
import java.lang.Math.PI
import kotlin.math.cos
import kotlin.math.sin

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            Surface (
                color = Color(0XFF101010),
                modifier = Modifier
                    .fillMaxSize()
            ) {
                Box(
                    contentAlignment = Alignment.Center,
                )  {
                    Timer(
                        totalTime = 100L * 1000L,
                        handleColor = Color.Green,
                        inactiveBarColor = Color.DarkGray,
                        activeBarColor = Color(0xFF37B900),
                        modifier = Modifier.size(200.dp)
                    )
                }
            }

        }
    }
}

@Composable
fun Timer(
    totalTime: Long,
    handleColor: Color,
    inactiveBarColor: Color,
    activeBarColor: Color,
    modifier: Modifier = Modifier,
    initialValue: Float = 1f,
    strokeWidth: Dp = 5.dp
) {
    // define a state for the size of the Box that will hold the timer
    var size by remember {
        mutableStateOf(IntSize.Zero)
    }

    // define a state for the current value of the timer
    var currentTime by remember {
        mutableStateOf(totalTime)
    }

    // defines how much of the timer is left until it goes off
    var value by remember {
        mutableStateOf(initialValue)
    }

    // define a state for the current execution state of the timer
    var isTimerRunning by remember {
        mutableStateOf(false)
    }

    // gives us access to CoroutineScope also
    // the code in the block will run every time the key's value changes
    LaunchedEffect(key1 = currentTime, key2 = isTimerRunning) {
        if (currentTime > 0 && isTimerRunning) {
            delay(100L)
            currentTime -= currentTime
            value = currentTime/ totalTime.toFloat()
        }
    }

    // define the UI
    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier
            // called whenever the size of the composable changes
            .onSizeChanged {
                // set size to the new size of the Box
                size = it
            }
    ) {
        // create a Canvas for the timer UI
        Canvas(modifier = modifier) {
            /*
            Explanation: create two arcs on top of each other (one will represent the inactive bar
            and the other one the active bar)
             */

            // define the inactive bar
            drawArc(
                color = inactiveBarColor,
                startAngle = -250f,
                sweepAngle = 250f,
                useCenter = false,
                size = Size(size.width.toFloat(), size.height.toFloat()),
                style = Stroke(strokeWidth.toPx(), cap = StrokeCap.Round)
            )

            // define the active bar
            drawArc(
                color = activeBarColor,
                startAngle = -250f,
                sweepAngle = 250f * value,
                useCenter = false,
                size = Size(size.width.toFloat(), size.height.toFloat()),
                style = Stroke(strokeWidth.toPx(), cap = StrokeCap.Round)
            )

            // define the circle visual aid
            val center = Offset(size.width/2f, size.height/2f) // size is the current size of the Box
            val beta = ((250f * value + 140f) * PI / 180f).toFloat()
            val r = size.width / 2f
            val a = cos(beta) * r
            val b = sin(beta) * r

            drawPoints(
                listOf(Offset((center.x) +a, (center.y) +b)),
                pointMode = PointMode.Points,
                color = handleColor,
                strokeWidth = (strokeWidth * 3f).toPx(),
                cap = StrokeCap.Round
            )
        }

        Text(
            text = (currentTime/ 1000L).toString(),
            fontSize = 44.sp,
            fontWeight = FontWeight.Bold,
            color = Color.White
        )

        Button(
            onClick = {
                      if (currentTime <= 0L) {
                          currentTime = totalTime
                          isTimerRunning = true
                      } else {
                          isTimerRunning = !isTimerRunning
                      }
            },
            modifier = modifier
                .align(Alignment.BottomCenter)
                .size(20.dp),

            // define multiple colors for different states of the button
            colors = ButtonDefaults.buttonColors(
                backgroundColor = if(!isTimerRunning || currentTime <= 0) {
                    Color.Green
                } else {
                    Color.Red
                }
            )
        ) {
            Text(
                text =
                if (isTimerRunning && currentTime >= 0) "Stop"
                else if (!isTimerRunning && currentTime >= 0L) "Start"
                else "Restart"
            )
        }
    }
}

