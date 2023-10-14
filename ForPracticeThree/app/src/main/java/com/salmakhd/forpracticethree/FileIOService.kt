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

interface FileIOService {
    fun writeLayoutToPdf(outputStream: OutputStream)
}
