
package com.salmakhd.forpracticelocal

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.result.contract.ActivityResultContracts.CreateDocument
import androidx.documentfile.provider.DocumentFile
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext
import java.io.PrintWriter
import java.text.SimpleDateFormat
import java.util.Locale

const val CREATE_DOCUMENT_REQUEST_CODE = 10000
const val NIRVANA_REPORT_DIR_NAME = "Nirvana Report"
class DocumentManagerOld constructor(): ComponentActivity() {
    var treeUri: String? = null
    private val dateFormatter = SimpleDateFormat(
        "yyyy.MM.dd 'at' HH:mm:ss z",
        Locale.getDefault()
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // if treeUri is empty or null, show dialog to allow the user to pick a folder
        openSAFDocumentTreeLauncher("nirvana").launch(Uri.EMPTY)

        runBlocking {
            delay(2000L)
            // writeSAFDocumentLauncher("nirvana").launch("nirvana")
            writeViaTree("nirvana")
        }
    }

    private fun writeSAFDocumentLauncher(fileContent: String) = registerForActivityResult(
        object : CreateDocument(Intent.ACTION_CREATE_DOCUMENT) {
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

    private suspend fun writeViaTree(fileName: String) =
        ""?.toUriOrNullIfNoPermission()?.createFileInTree(fileName) ?: openSAFDocumentTreeLauncher(
            fileName
        ).launch(
            null
        )

    private fun String.toUriOrNullIfNoPermission() =
        if (contentResolver.persistedUriPermissions.any {
                it.uri.toString() == this && it.isReadPermission && it.isWritePermission
            }) Uri.parse(this)
        else null

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
        val dir = DocumentFile.fromTreeUri(this@DocumentManagerOld, this@createFileInTree)
            ?: throw RuntimeException("Error loading ${this@createFileInTree}")
        val nirvanaDir = dir.findFile("Nirvana Report") ?: dir.createDirectory("Nirvana Report")
        ?: throw RuntimeException("Error loading ${this@createFileInTree}")

        val file = nirvanaDir.createFile("application/pdf", fileName)
            ?: throw RuntimeException("Could not create $fileName")
        contentResolver.openOutputStream(file.uri)?.use { outputStream ->
            PrintWriter(outputStream).use { pw ->
                pw.print("trtrtrtrtrtrtrtrrtrt")
            } ?: throw RuntimeException("Could not open output stream for ${file.uri}")
            dumpSAFTree()

        }
    }

    private suspend fun dumpSAFTree() = withContext(Dispatchers.IO) {
        val uri = Uri.parse(requireNotNull(treeUri))
        val dir =
            DocumentFile.fromTreeUri(this@DocumentManagerOld, uri) ?: throw RuntimeException("")

        val nirvanaDirectory = dir.findFile(NIRVANA_REPORT_DIR_NAME) ?: throw RuntimeException("")
        nirvanaDirectory.listFiles().joinToString("\n") { it.name ?: "(no name)" }
    }
}

//
//    fun fff() {
//        val contentResolver = applicationContext.contentResolver
//
//        private fun alterDocument(uri: Uri) {
//            try {
//                contentResolver.openFileDescriptor(uri, "w")?.use {
//                    FileOutputStream(it.fileDescriptor).use {
//                        it.write(
//                            ("Overwritten at ${System.currentTimeMillis()}\n")
//                                .toByteArray()
//                        )
//                    }
//                }
//            } catch (e: FileNotFoundException) {
//                e.printStackTrace()
//            } catch (e: IOException) {
//                e.printStackTrace()
//            }
//        }
//
//    }

/*
val outputDirectory = File(applicationContext.filesDir, OUTPUT_PATH)
if (outputDirectory.exists()) {
    val entries = outputDirectory.listFiles()
    if (entries != null) {
        for (entry in entries) {
            val name = entry.name
            if (name.isNotEmpty() && name.endsWith(".png")) {
                val deleted = entry.delete()
                Log.i(TAG, "Deleted $name - $deleted")
            }
        }
    }
}

private val title = "Blurred Image"
private val dateFormatter = SimpleDateFormat(
    "yyyy.MM.dd 'at' HH:mm:ss z",
    Locale.getDefault()
)


@Throws(FileNotFoundException::class)
fun writeBitmapToFile(applicationContext: Context, bitmap: Bitmap): Uri {
    val name = String.format("blur-filter-output-%s.png", UUID.randomUUID().toString())
    val outputDir = File(applicationContext.filesDir, OUTPUT_PATH)
    if (!outputDir.exists()) {
        outputDir.mkdirs() // should succeed
    }
    Trace.beginSection("dd")
    val outputFile = File(outputDir, name)
    var out: FileOutputStream? = null
    try {
        out = FileOutputStream(outputFile)
        bitmap.compress(Bitmap.CompressFormat.PNG, 0 /* ignored for PNG */, out)
    } finally {
        out?.let {
            try {
                it.close()
            } catch (e: IOException) {
                Log.e(TAG, e.message.toString())
            }
        }
    }
    return Uri.fromFile(outputFile)
}


fun Context.getImageUri(): Uri {
    val resources = this.resources

    return Uri.Builder()
        .scheme(ContentResolver.SCHEME_ANDROID_RESOURCE)
        .authority(resources.getResourcePackageName(R.drawable.android_cupcake))
        .appendPath(resources.getResourceTypeName(R.drawable.android_cupcake))
        .appendPath(resources.getResourceEntryName(R.drawable.android_cupcake))
        .build()
}

val contentResolver = applicationContext.contentResolver

private fun alterDocument(uri: Uri) {
    try {
        contentResolver.openFileDescriptor(uri, "w")?.use {
            FileOutputStream(it.fileDescriptor).use {
                it.write(
                    ("Overwritten at ${System.currentTimeMillis()}\n")
                        .toByteArray()
                )
            }
        }
    } catch (e: FileNotFoundException) {
        e.printStackTrace()
    } catch (e: IOException) {
        e.printStackTrace()
    }
}


 */