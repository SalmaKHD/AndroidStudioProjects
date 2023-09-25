/*
Topic of investigation: motion layout in Jetpack Compose
 */
package com.salmakhd.android.forpractice

import android.widget.Space
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Icon
import androidx.compose.material.Slider
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Share
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ExperimentalMotionApi
import androidx.constraintlayout.compose.MotionLayout
import androidx.constraintlayout.compose.MotionScene
import androidx.constraintlayout.compose.layoutId

@OptIn(ExperimentalMotionApi::class)
@Composable
fun CustomMotionLayout(
    progress: Float
) {
    val context = LocalContext.current
    val motionScene = remember {
        context.resources
            .openRawResource(R.raw.motion_scene)
            .readBytes()
            .decodeToString()
    }
    
    MotionLayout(
        motionScene = MotionScene(content = motionScene),
        progress = progress,
        modifier = Modifier.fillMaxWidth()
    ) {
        val props = motionProperties(id = "text")
        Image(painter = painterResource(id = R.drawable.ic_launcher_background), contentDescription = "", modifier = Modifier.layoutId("my_image"))
        
        Icon(
            modifier = Modifier.layoutId("back_icon"),
            imageVector = Icons.Filled.ArrowBack,
            contentDescription = null,
        )

        Text(
            text = "Salma",
            fontSize = 16.sp,
            color = props.value.color("text_color"),
            modifier = Modifier.layoutId("text")
        )

        Icon(
            modifier = Modifier.layoutId("share_icon"),
            imageVector = Icons.Filled.Share,
            contentDescription = null,
        )
    }
}

@Preview
@Composable
fun CustomMotionLayoutPreview() {
    Column(modifier = Modifier.fillMaxSize()) {
        var isShow by remember { mutableStateOf(false) }
        val progress by animateFloatAsState(targetValue =
        if(isShow) 1f else 0f,
            animationSpec = tween(durationMillis = 2000)
        )

        CustomMotionLayout(progress = progress)

        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick ={ isShow = !isShow
        }) {
            Text(text = "Start motion")
        }        
    }
}