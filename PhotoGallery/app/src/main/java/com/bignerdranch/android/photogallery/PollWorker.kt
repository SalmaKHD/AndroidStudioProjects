package com.bignerdranch.android.photogallery

import android.app.PendingIntent
import android.content.Context
import android.util.Log
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import kotlinx.coroutines.flow.first

private const val TAG = "PollWorker"
class PollWorker (
    private val context: Context,
    workerParameters: WorkerParameters
): CoroutineWorker(context, workerParameters) { // extend CoroutineWorker class to do work in the background
    // execute the work in a background thread
    override suspend fun doWork(): Result {
        val preferencesRepository = PreferencesRepository.get()
        val photoRepository = PhotoRepository()
        val query = preferencesRepository.storedQuery.first()
        val lastId = preferencesRepository.lastResultId.first()
        if (query.isEmpty()) {
            Log.i(TAG, "No saved query, finishing early.")
            return Result.success()
        }
        // indicate that the work was completed successfully
        /*
        Notes: a failure result means the work will never run again.
        A retry result can also be set
         */
        return try {
            // if the user has a last search item saved in their filesystem,
            // fetch photos based on the query
            val items = photoRepository.searchPhotos(query)
            if (items.isNotEmpty()) {
                // compare the id of the first photo to the id saved in the file to see
                // if new photos can to be fetched
                val newResultId = items.first().id
                if (newResultId == lastId) {
                    Log.i(TAG, "Still have the same result: $newResultId")
                } else {
                    Log.i(TAG, "Got a new result: $newResultId")
                    // change the id of the first photo item in the file
                    preferencesRepository.setLastResultId(newResultId)

                    // push a notification and allow the user to launch the app
                    // by pressing the notification
                    notifyUser()
                }
            }
            // return success
            Result.success()
        } catch (ex: Exception) {
            // if any exceptions occur when executing the work, terminate the worker
            Log.e(TAG, "Background update failed", ex)
            Result.failure()
        }
    }

    // push a notification and launch app upon it being pressed
    private fun notifyUser() {
        // retrieve an Intent that launches the app
        val intent = MainActivity.newIntent(context)

        // creating a PendingIntent object that will be used by the Notification object
        val pendingIntent = PendingIntent.getActivity(
            context,
            0,
            intent,
            PendingIntent.FLAG_IMMUTABLE
        )

        val resources = context.resources
        // create notification
        /*
       NotificationCompat: for backwards compatibility with devices that have API < 26
        */
        val notification = NotificationCompat
                // channel ids will be used with devices that have APIs < 26
            .Builder(context, NOTIFICATION_CHANNEL_ID)
                // ticker will be used by accessibility services
            .setTicker(resources.getString(R.string.new_pictures_title))
            .setSmallIcon(android.R.drawable.ic_menu_report_image)
            .setContentTitle(resources.getString(R.string.new_pictures_title))
            .setContentText(resources.getString(R.string.new_pictures_text))
            .setContentIntent(pendingIntent)
                // instruct the OS to eliminate the notification when it's pressed
            .setAutoCancel(true)
            .build()

        // push notification
        // Note: the id must be unique for each notification, but it's reusable
        NotificationManagerCompat.from(context).notify(0, notification)
    }

}