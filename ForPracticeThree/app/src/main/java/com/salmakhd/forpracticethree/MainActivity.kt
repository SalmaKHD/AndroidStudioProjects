package com.salmakhd.forpracticethree

import android.Manifest
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.pdf.PdfDocument
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.os.Environment
import android.provider.DocumentsContract
import android.util.DisplayMetrics
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Paint
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.app.ActivityCompat.startActivityForResult
import androidx.documentfile.provider.DocumentFile
import androidx.lifecycle.lifecycleScope
import com.salmakhd.forpracticelocal.NIRVANA_REPORT_DIR_NAME
import com.salmakhd.forpracticethree.ui.theme.ForPracticeThreeTheme
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext
import java.io.File
import java.io.OutputStream
import java.io.PrintWriter
import java.text.SimpleDateFormat
import java.util.Locale

class MainActivity : ComponentActivity() {
    private val dateFormatter = SimpleDateFormat(
        "yyyy.MM.dd 'at' HH:mm:ss z",
        Locale.getDefault()
    )

    var treeUri: String? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestPermissions(arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE), 1)

        // if treeUri is empty or null, show dialog to allow the user to pick a folder
        openSAFDocumentTreeLauncher("nirvana").launch(Uri.parse(Environment.DIRECTORY_DOCUMENTS))

        // if treeUri is not empty or null, just get the uri and create the file directly
        setContent {
            ForPracticeThreeTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                }
            }
        }
    }

    private fun createPdf() {

    }

    private fun convertXmlToPdf(outputStream: OutputStream) {
        val view = LayoutInflater.from(this).inflate(R.layout.pdf, null)
        val displayMetrics = DisplayMetrics()
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            this.display?.getRealMetrics(displayMetrics)

        } else {
            this.windowManager.defaultDisplay?.getMetrics(displayMetrics)

        }

        view.measure(View.MeasureSpec.makeMeasureSpec(displayMetrics.widthPixels, View.MeasureSpec.EXACTLY),
            View.MeasureSpec.makeMeasureSpec(displayMetrics.heightPixels, View.MeasureSpec.EXACTLY))

        view.layout(0,0, displayMetrics.widthPixels, displayMetrics.heightPixels)

        val image = view.findViewById<View>(R.id.imageView)
        val document = PdfDocument()
        val width = view.measuredWidth
        val height = view.measuredHeight

        val pageInfo = PdfDocument.PageInfo.Builder(width, height, 1).create()
        val page = document.startPage(pageInfo)

        val canvas = page.canvas
        val bitMap = loadBitmapFromView(width, height, view)
        canvas.drawBitmap(bitMap, 0f, 0f, null)

        view.draw(canvas)

        document.finishPage(page)
        document.writeTo(outputStream)
        document.close()
    }

    private fun loadBitmapFromView(width: Int, height: Int, view: View): Bitmap {
       return Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888)
    }
    private fun writeSAFDocumentLauncher(fileContent: String) = registerForActivityResult(
        object : ActivityResultContracts.CreateDocument(Intent.ACTION_CREATE_DOCUMENT) {
            override fun createIntent(context: Context, input: String): Intent {
                return super.createIntent(context, input).apply {
                    (Intent.CATEGORY_OPENABLE)
                    type = "application/pdf"
                }
            }
        }
    ) { uri ->
        if (uri != null) {

            contentResolver.openOutputStream(uri)?.use { outputStream ->
                 PrintWriter(outputStream).use { printWriter ->
                    fileContent
                }

            }
        }
    }

    private fun openSAFDocumentTreeLauncher(fileName: String) =
        registerForActivityResult(ActivityResultContracts.OpenDocumentTree()) { uri ->
            if (uri != null) {
                treeUri = uri.toString()
                val takeFlags: Int =
                    Intent.FLAG_GRANT_READ_URI_PERMISSION or Intent.FLAG_GRANT_WRITE_URI_PERMISSION
                contentResolver.takePersistableUriPermission(uri, takeFlags)
                lifecycleScope.launch {
                    uri.createFileInTree(fileName)
                }
            }

        }

    private suspend fun Uri.createFileInTree(fileName: String) = withContext(Dispatchers.IO) {
        val dir = DocumentFile.fromTreeUri(this@MainActivity, this@createFileInTree)
            ?: throw RuntimeException("Error loading ${this@createFileInTree}")
        val nirvanaDir = dir.findFile("Nirvana Report") ?: dir.createDirectory("Nirvana Report")
        ?: throw RuntimeException("Error loading ${this@createFileInTree}")

        val file = nirvanaDir.createFile("application/pdf", fileName) ?: throw RuntimeException("Could not create $fileName")

        contentResolver.openOutputStream(file.uri)?.use { outputStream ->

            convertXmlToPdf(outputStream)
//            val pdfDocument = PdfDocument()
//            val pageInfo = PdfDocument.PageInfo.Builder(1080, 1920, 1).create() // file properties
//            val page = pdfDocument.startPage(pageInfo) // actual document
//
//            val canvas = page.canvas // used to write things to the file
//            val paint = android.graphics.Paint()
//           // paint.color = 565454
//
//            val text = "Hello World";
//            val x = 500f
//            val y = 900f
//            canvas.drawText(text, x, y, paint)
//            canvas.drawText(text, x, y, paint)
//            pdfDocument.finishPage(page)
//            pdfDocument.writeTo(outputStream)
           // pdfDocument.close()
            outputStream.close()
            dumpSAFTree()
        }
    }
    private suspend fun dumpSAFTree() = withContext(Dispatchers.IO) {
        val uri = Uri.parse(requireNotNull(treeUri))
        val dir = DocumentFile.fromTreeUri(this@MainActivity, uri) ?: throw RuntimeException("")

        val nirvanaDirectory = dir.findFile(NIRVANA_REPORT_DIR_NAME) ?: throw RuntimeException("")
        nirvanaDirectory.listFiles().joinToString("\n") { it.name ?: "(no name)" }
    }

    private suspend fun writeViaTree(fileName: String) {
        Log.e("3636", "$fileName was just created.")
        treeUri?.toUriOrNullIfNoPermission()?.createFileInTree(fileName)
            ?: openSAFDocumentTreeLauncher(fileName).launch(
                null
            )
    }

    private fun String.toUriOrNullIfNoPermission() =
        if (contentResolver.persistedUriPermissions.any {
                it.uri.toString() == this && it.isReadPermission && it.isWritePermission
            }) Uri.parse(this)
        else null

}

