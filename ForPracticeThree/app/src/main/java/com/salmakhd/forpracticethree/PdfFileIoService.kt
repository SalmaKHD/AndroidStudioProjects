package com.salmakhd.forpracticethree

import android.app.Activity
import android.graphics.Bitmap
import android.graphics.pdf.PdfDocument
import android.os.Build
import android.util.DisplayMetrics
import android.view.LayoutInflater
import android.view.View
import android.widget.TextView
import java.io.OutputStream

class PdfFileIoService constructor(
    private val context: Activity
): FileIOService {
    // TODO: ACCORDING TO THE FORMAT,
    //  DETERMINE A MAX ITEM # ON EACH PAGE,
    //  PASS LIST AS ARGUMENT AND PUT ON EACH PAGE
    // TODO: FIX NOT WRITING IMAGES
    // TODO: DO IO OPERATIONS ON IO DISPATCHER
    // FINISH CURRENT PAGE AND START THE NEW ONE IN THE BEGINNING AT END OF THE LOOP, SEE @MEDIUM
    override fun writeLayoutToPdf(outputStream: OutputStream) {
        val view = LayoutInflater.from(context).inflate(R.layout.pdf, null)
        val textView =  view.findViewById<TextView>(R.id.textView)
        textView.text = "blaaaaaaaahhhhhhhhhhhhhhhhhhhhhhh"

        val displayMetrics = DisplayMetrics()
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            context.display?.getRealMetrics(displayMetrics)

        } else {
            context.windowManager.defaultDisplay?.getMetrics(displayMetrics)

        }
        view.measure(
            View.MeasureSpec.makeMeasureSpec(displayMetrics.widthPixels, View.MeasureSpec.EXACTLY),
            View.MeasureSpec.makeMeasureSpec(displayMetrics.heightPixels, View.MeasureSpec.EXACTLY))

        view.layout(0,0, displayMetrics.widthPixels, displayMetrics.heightPixels)

        val document = PdfDocument()
        val width = view.measuredWidth
        val height = view.measuredHeight

        val pageInfo = PdfDocument.PageInfo.Builder(width, height, 1).create()
        val page = document.startPage(pageInfo)

        val canvas = page.canvas
        val bitMap = loadBitmapFromView(width, height)

//        load(width, height, view)?.let {
//            page.canvas.drawBitmap(
//                it,
//                0f,
//                0f,
//                null
//            )
//        }

        canvas.drawBitmap(bitMap, 0f,0f,null)
        view.draw(canvas)
        document.finishPage(page)
        document.writeTo(outputStream)
        document.close()
    }

    private fun loadBitmapFromView(width: Int, height: Int): Bitmap {
        return Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888)
    }

    private fun load(width: Int, height: Int, v: View): Bitmap {
        val specWidth: Int =
            View.MeasureSpec.makeMeasureSpec(width, View.MeasureSpec.EXACTLY)
        val specHeight: Int =
            View.MeasureSpec.makeMeasureSpec(height, View.MeasureSpec.EXACTLY)
        v.measure(specWidth, specHeight)
        val requiredWidth: Int = v.measuredWidth
        val requiredHeight: Int = v.measuredHeight
        val b = Bitmap.createBitmap(requiredWidth, requiredHeight, Bitmap.Config.ARGB_8888)
        val c = android.graphics.Canvas(b)
        //c.drawColor(Color.WHITE)
        v.layout(v.left, v.top, v.right, v.bottom)
        v.draw(c)
        return b
    }
}