package com.plcoding.meditationuiyoutube

import androidx.annotation.DrawableRes
import androidx.compose.ui.graphics.Color

// define the UI properties for each feature in the grid
data class Feature(
    val title: String,
    @DrawableRes val iconId: Int,
    val lightColor: Color,
    val mediumColor: Color,
    val darkColor: Color
)
