package com.salmakhd.forpracticethree

import androidx.activity.result.ActivityResultLauncher
import java.io.OutputStream
import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.util.Calendar
import java.util.Date
import java.util.Locale
abstract class FileManager {
    private val dateFormatter = SimpleDateFormat(
        "yyyy.MM.dd-HH:mm:ss z",
        Locale.getDefault()
    )
    private val calendar: Calendar = Calendar.getInstance()
    private var dateTimeStr = ""
    init {
        val year = calendar.get(Calendar.YEAR);
        val month = calendar.get(Calendar.MONTH); // Month is zero-based (0 for January)
        val day = calendar.get(Calendar.DAY_OF_MONTH);
        dateTimeStr = "$year.$month.$day"
    }
    abstract fun writeSAFDocumentLauncher(fileContent: String): ActivityResultLauncher<String>
    abstract suspend fun createDocumentAndWrite(fileName: String = "nirvana-report_" +
            "$dateTimeStr", writeToFile: (OutputStream) -> Unit)
}