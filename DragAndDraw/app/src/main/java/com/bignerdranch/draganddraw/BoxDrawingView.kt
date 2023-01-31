package com.bignerdranch.draganddraw

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.PointF
import android.util.AttributeSet
import android.util.Log
import android.view.MotionEvent
import android.view.View

private const val TAG = "BoxDrawingView"
// create a "simple" custom view
class BoxDrawingView(
    context: Context,
    attrs: AttributeSet? = null
) : View(context, attrs) {

    // keep track of the boxes that are to be created
    private var currentBox: Box? = null
    private val boxes = mutableListOf<Box>()

    // create two Paint objects
    // responsible for the characteristics of all the shapes drawn on screen
    private val boxPaint = Paint().apply {
        color = 0x22ff0000.toInt()
    }
    private val backgroundPaint = Paint().apply {
        color = 0xfff8efe0.toInt()
    }

    // called when a touch event happens
    override fun onTouchEvent(event: MotionEvent): Boolean {
        // create a point from the X and Y coordinates of where the event happened
        val current = PointF(event.x, event.y)

        // identify the action that has occurred
        var action = ""
        when (event.action) {
            MotionEvent.ACTION_DOWN -> {
                action = "ACTION_DOWN"

                // as soon as this event happens, create a new Box instance that will later on be
                // added to the list of Box objects
                currentBox = Box(current).also {
                    boxes.add(it)
                }
            }
            MotionEvent.ACTION_MOVE -> {
                action = "ACTION_MOVE"

                // update the end property of the box
                updateCurrentBox(current)
            }
            MotionEvent.ACTION_UP -> {
                action = "ACTION_UP"

                // update the end property of the box and set the object to null
                // so it won't be used anymore
                updateCurrentBox(current)
                currentBox = null
            }
            MotionEvent.ACTION_CANCEL -> {
                action = "ACTION_CANCEL"
            }
        }
        Log.i(TAG, "$action at x=${current.x}, y=${current.y}")
        return true
    }

    override fun onDraw(canvas: Canvas) {
    // Fill the background
        canvas.drawPaint(backgroundPaint)
        boxes.forEach { box ->
            canvas.drawRect(box.left, box.top, box.right, box.bottom, boxPaint)
        }
    }

    private fun updateCurrentBox(current: PointF) {
        currentBox?.let {
            it.end = current
            // will force the view to render itself
            invalidate() // calls onDraw() which draws a box
        }
    }


}