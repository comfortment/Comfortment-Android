package com.comfortment.presentation.ui.notification

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.media.RingtoneManager
import android.os.Build
import android.util.Log
import androidx.core.app.NotificationCompat
import com.comfortment.R
import com.comfortment.presentation.ui.MainActivity
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage

class DamageNotifyService : FirebaseMessagingService() {

    override fun onMessageReceived(remoteMessage: RemoteMessage?) {
        if (remoteMessage != null) {
            if (remoteMessage.notification != null) {
                sendNotification(remoteMessage.notification?.title!!, remoteMessage.notification?.body!!)
            }
        }
    }

    override fun onNewToken(token: String?) {
        Log.e("FCM TOKEN", token)
    }

    private fun sendNotification(title: String, messageBody: String) {
        val intent = Intent(this, MainActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        val pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_ONE_SHOT)

        val defaultSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)

        val notification = NotificationCompat.Builder(applicationContext, "Comfortment").apply {
            setSmallIcon(R.drawable.ic_logo)
            setContentTitle(title)
            setContentText(messageBody)
            setAutoCancel(true)
            setSound(defaultSoundUri)
            setContentIntent(pendingIntent)
        }.build()

        val notificationManager =
            applicationContext.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel("Comfortment", "Comfortment", NotificationManager.IMPORTANCE_HIGH)
            channel.description = "Comfortment Notify"
            channel.setShowBadge(false)

            notificationManager.createNotificationChannel(channel)
        }
        notificationManager.notify(20010127, notification)
    }
}
