package br.com.jrmantovani.copacatar.work.extensions

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import br.com.jrmantovani.copacatar.R

private const val CHANNEL_ID = "new_channel_video"
private const val NOTIFICATION_NAME = "Notificações"
private const val NOTIFICATION_INTENT_REQUEST_CODE = 0

@RequiresApi(Build.VERSION_CODES.O)
fun Context.showNotification(title: String, content: String) {
    createNotificationChannel()
    val notification = getNotification(title, content)

    NotificationManagerCompat
        .from(this)
        .notify(content.hashCode(), notification)
}

@RequiresApi(Build.VERSION_CODES.O)
private fun Context.createNotificationChannel() {
    val importance = NotificationManager.IMPORTANCE_HIGH
    val channel = NotificationChannel(CHANNEL_ID, NOTIFICATION_NAME, importance)

    (getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager)
        .createNotificationChannel(channel)
}

private fun Context.getNotification(title: String, content: String): Notification =
    NotificationCompat
        .Builder(this, CHANNEL_ID)
        .setSmallIcon(R.drawable.ic_soccer)
        .setContentTitle(title)
        .setContentText(content)
        .setPriority(NotificationCompat.PRIORITY_HIGH)
        .setCategory(NotificationCompat.CATEGORY_RECOMMENDATION)
        .setVisibility(NotificationCompat.VISIBILITY_PUBLIC)
        .setContentIntent(getOpenAppPendingIntent())
        .setAutoCancel(true)
        .build()

private fun Context.getOpenAppPendingIntent() = PendingIntent.getActivity(
    this,
    NOTIFICATION_INTENT_REQUEST_CODE,
    packageManager.getLaunchIntentForPackage(packageName),
    PendingIntent.FLAG_IMMUTABLE or PendingIntent.FLAG_UPDATE_CURRENT,
)
