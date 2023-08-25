package com.salmakhd.android.forpractice

import android.annotation.SuppressLint
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.core.content.ContextCompat
import java.time.LocalDate
import java.util.GregorianCalendar

const val MAIN_TAG = "Main"
const val REMINDERS_CHANNEL_ID = "ff"
const val REMINDERS_NOTIFICATION_GROUP = "ff"
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val year = LocalDate.now().year
            val month = LocalDate.now().month.value
            val day = LocalDate.now().dayOfMonth
            Log.i(MAIN_TAG, "today's date in solar calendar is: ${JalaliCalendar(GregorianCalendar(year, month, day))}")
            Log.i(MAIN_TAG,"modulo of random1: ${511275%100}")
            Log.i(MAIN_TAG,"modulo of random1: ${404934%100}")
            Log.i(MAIN_TAG,"modulo of random1: ${303313%100}")
            Log.i(MAIN_TAG,"modulo of random1: ${476597%100}")
            Log.i(MAIN_TAG,"modulo of random1: ${583382%100}")
        }
    }
}

const val SYSTEM_NOTIFIER_TAG = "System_Notifier_Tag"
class SystemNotifier constructor(
   private val context: Context
) {

    @SuppressLint("MissingPermission")
    fun postNotification(): Unit = with(context) {
        // don't post notification if permission is not granted

        if(!context.isPermissionGranted(android.Manifest.permission.POST_NOTIFICATIONS)) {
            Log.i(SYSTEM_NOTIFIER_TAG, "Notification permission is NOT granted: Returning...")
            return
        }

        var builder = NotificationCompat.Builder(this, REMINDERS_CHANNEL_ID) // for v. > 8.0
            .setSmallIcon(R.drawable.ic_launcher_background) // show app icon
            .setContentTitle("FFF")
            .setContentText("FFFF")
            .setPriority(NotificationCompat.PRIORITY_DEFAULT) // for v. <= 7.1
            .setGroup(REMINDERS_NOTIFICATION_GROUP)
            // .setLights(Color(colorResource(id = R.color.nirvana_top)))
            //.setTimeoutAfter() set if needed
            .setAutoCancel(true)

//        // convert to extended notification if applicable
//        notificationResource.bigText?.let {
//            builder = builder.setStyle(NotificationCompat.BigTextStyle()
//                .bigText(it)) // provides a template for notification appearance
//        }

        // add tap behavior if applicable
//        notificationResource.deepLinkUri?.let {
//            builder = builder.setContentIntent(createReminderNotificationIntent(it.toUri(), notificationResource.notificationId))
//        }

        val notificationManager = NotificationManagerCompat.from(this)
        createNotificationChannelIfRequired()
        notificationManager.notify(
           9,
            builder.build()
        )
    }

    /**
     * register a notification channel on devices with v. > 8.0 aka Oreo
     */
    private fun Context.createNotificationChannelIfRequired() {

        if(Build.VERSION.SDK_INT < Build.VERSION_CODES.O) return // not needed

        val name = "NOTIFICATION CHANNEL NAME"
        val descriptionText = "NOTIFICATION DESCRIPTION TEXT"
        val importance = NotificationManager.IMPORTANCE_DEFAULT // importance of the notification channel
        val channel = NotificationChannel(REMINDERS_CHANNEL_ID, name, importance).apply {
            description = descriptionText
        }

        // register the channel
        NotificationManagerCompat.from(this).createNotificationChannel(channel)
    }

    // define actions for notifications
    /**
     * @param uri for navigating to target screen
     * ex:
     *
     * ```
     * "$DEEP_LINK_SCHEME_AND_HOST/$NIRVANA_PATH/$id".toUri()
     * ```
     */
    private fun Context.createReminderNotificationIntent(uri: Uri, notificationRequestCode: Int): PendingIntent? {
        val intent = Intent().apply {
            action = Intent.ACTION_VIEW
            data = uri
            component = ComponentName(
                packageName,
                TARGET_ACTIVITY_NAME
            )
        }

        return PendingIntent.getActivity(
            this,
            notificationRequestCode,
            intent,
            PendingIntent.FLAG_IMMUTABLE
        )
    }
}

fun Context.isPermissionGranted(permission: String): Boolean =
    ContextCompat.checkSelfPermission(this, permission) == PackageManager.PERMISSION_GRANTED

