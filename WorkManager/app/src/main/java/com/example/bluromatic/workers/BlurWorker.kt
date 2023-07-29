package com.example.bluromatic.workers

import android.content.Context
import android.graphics.BitmapFactory
import android.net.Uri
import android.util.Log
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import androidx.work.workDataOf
import com.example.bluromatic.DELAY_TIME_MILLIS
import com.example.bluromatic.KEY_BLUR_LEVEL
import com.example.bluromatic.KEY_IMAGE_URI
import com.example.bluromatic.R
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext

private const val TAG = "BlurWork"
class BlurWorker(
    context: Context,
    params: WorkerParameters
): CoroutineWorker(context, params) {
    // Worker class vs. CoroutineWorker class?
    // doWork() in CoroutineWorker a suspend function
    override suspend fun doWork(): Result {
        val resourceUri = inputData.getString(KEY_IMAGE_URI)
        val blurLevel = inputData.getInt(KEY_BLUR_LEVEL, 1)

        require(!resourceUri.isNullOrBlank()) {
            val errorMessage = applicationContext.getString(R.string.invalid_input_uri)
            Log.e(TAG, errorMessage)
        }

        makeStatusNotification(
            message = applicationContext.getString(R.string.blurring_image),
            context = applicationContext
        )

        return withContext(Dispatchers.IO) {
            return@withContext try {
                // simulate long operation
                delay(DELAY_TIME_MILLIS)

                val resolver = applicationContext.contentResolver
                val picture = BitmapFactory.decodeStream(
                    resolver.openInputStream(Uri.parse(resourceUri))
                )

                val output = blurBitmap(picture, blurLevel)

                val outputUri = writeBitmapToFile(
                    applicationContext,
                    output
                )

                // create a Data object
                val outputData = workDataOf(KEY_IMAGE_URI to outputUri)
                Result.success(outputData)
            } catch (throwable: Throwable) {
                Log.e(TAG, applicationContext.getString(R.string.error_applying_blur))
                Result.failure()
            }
        }
    }
}