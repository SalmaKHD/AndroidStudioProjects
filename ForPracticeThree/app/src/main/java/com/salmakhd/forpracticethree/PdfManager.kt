package com.salmakhd.forpracticethree

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Environment
import android.util.Log
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.result.contract.ActivityResultContracts.CreateDocument
import androidx.documentfile.provider.DocumentFile
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.io.OutputStream
import java.io.PrintWriter

const val NIRVANA_DIR_NAME = "Nirvana Report"

/*
TODO:
 */
class PdfManager constructor(
    private val nirvanaPreferencesRepository: Int,
    private val context: MainActivity
): FileManager() {
    private var treeUri: String?
    init {
        treeUri = null //nirvanaPreferencesRepository.getTreeUri()
    }

    // Convert the File to a URI
    private var initialUri: Uri
    init {
        // Get the "Documents" directory in internal storage
        val documentsDir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS)
        initialUri =  Uri.fromFile(documentsDir)
    }

//    override suspend fun createDocument(fileName: String, writeToFile: (OutputStream) -> Unit) {
//        if (treeUri == null) {
//            openSAFDocumentTreeLauncher(fileName, writeToFile)
//        } else {
//            writeViaTree(fileName, writeToFile)
//        }
//    }
    override fun writeSAFDocumentLauncher(fileContent: String) = context.registerForActivityResult(
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
            context.contentResolver.openOutputStream(uri)?.use { outputStream ->
                PrintWriter(outputStream).use { printWriter ->
                    fileContent
                }
            }
        }
    }

    private fun openSAFDocumentTreeLauncher(fileName: String, writeToPdfFile: (OutputStream) -> Unit) =
        context.registerForActivityResult(ActivityResultContracts.OpenDocumentTree()) { uri ->
            if (uri != null) {
                treeUri = uri.toString()
                // nirvanaSharedPreferences.saveUri(uri);
                val takeFlags: Int =
                    Intent.FLAG_GRANT_READ_URI_PERMISSION or Intent.FLAG_GRANT_WRITE_URI_PERMISSION
                context.contentResolver.takePersistableUriPermission(uri, takeFlags)
                context.lifecycleScope.launch {
                    uri.createFileInTree(fileName, writeToPdfFile)
                }
            }
        }

    override suspend fun createDocumentAndWrite(fileName: String, writeToFile: (OutputStream) -> Unit) {
        Log.e("3636", "$fileName was just created.")
        treeUri?.toUriOrNullIfNoPermission()?.createFileInTree(fileName) { writeToFile(it) }
            ?: openSAFDocumentTreeLauncher(fileName, writeToFile).launch(
                initialUri
            )
    }

    private fun String.toUriOrNullIfNoPermission(): Uri? {
        return if (context.contentResolver.persistedUriPermissions.any {
                it.uri.toString() == this && it.isReadPermission && it.isWritePermission
            }) Uri.parse(this)
        else null
    }

    private suspend fun Uri.createFileInTree(fileName: String, writeToPdfFile: (OutputStream) -> Unit) = withContext(Dispatchers.IO) {
        val dir = DocumentFile.fromTreeUri(context, this@createFileInTree)
            ?: throw RuntimeException("Error loading ${this@createFileInTree}")

        val nirvanaDir = dir.findFile(NIRVANA_DIR_NAME) ?: dir.createDirectory(NIRVANA_DIR_NAME)
        ?: throw RuntimeException("Error loading ${this@createFileInTree}")

        val file = nirvanaDir.createFile("application/pdf", fileName) ?: throw RuntimeException("Could not create $fileName")

        context.contentResolver.openOutputStream(file.uri)?.use { outputStream ->
            writeToPdfFile(outputStream)
            outputStream.close()
            dumpSAFTree()

            val intent = Intent().apply {
                action = Intent.ACTION_VIEW
                setDataAndType(file.uri, "application/pdf")
            }
            val shareIntent = Intent.createChooser(intent, "فایل شما آماده است.")
            if(intent.resolveActivity(context.packageManager) != null) {
                context.startActivity(shareIntent)
            } else {
                Toast.makeText(context, "هیچ برنامه ای برای خواندن فایل موجود نیست.", Toast.LENGTH_LONG)
            }
        }
    }

    private suspend fun dumpSAFTree() = withContext(Dispatchers.IO) {
        val uri = Uri.parse(requireNotNull(treeUri))
        val dir = DocumentFile.fromTreeUri(context, uri) ?: throw RuntimeException("Directory does not exist!")

        val nirvanaDirectory = dir.findFile(NIRVANA_DIR_NAME) ?: throw RuntimeException("directory does not exit!")
        nirvanaDirectory.listFiles().joinToString("\n") { it.name ?: "(no name)" }
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
}

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