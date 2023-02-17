package com.plcoding.meditationuiyoutube

import androidx.annotation.DrawableRes

data class BottomMenuContent(
    val title: String,
    // this will make sure that the resource id is in fact valid at compile time
    @DrawableRes val iconId: Int
)
