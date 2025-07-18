package com.bignerdranch.draganddraw

import android.graphics.PointF

data class Box(val start: PointF) {
    var end: PointF = start

    // get the leftmost point on the box
    val left: Float
        get() = Math.min(start.x, end.x)

    // gee the rightmost point on the box
    val right: Float
        get() = Math.max(start.x, end.x)

    // get the topmost point on the box
    val top: Float
        get() = Math.min(start.y, end.y)

    // get the bottommost point on the box
    val bottom: Float
        get() = Math.max(start.y, end.y)
}
