package com.salmakhd.forpracticethree

import android.Manifest
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.lifecycle.lifecycleScope
import com.salmakhd.forpracticethree.ui.theme.ForPracticeThreeTheme
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestPermissions(arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE), 1)

        val pdfFileIoService = PdfFileIoService(this)
        val pdfManager = PdfManager(2, this)

        lifecycleScope.launch {
            pdfManager.createDocumentAndWrite(writeToFile = { pdfFileIoService.writeLayoutToPdf(it)})
        }

        // last step:
        // get the uri of the file and start an intent to open it.

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
}