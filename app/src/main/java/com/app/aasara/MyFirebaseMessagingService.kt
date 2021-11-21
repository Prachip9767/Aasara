package com.app.aasara

import com.google.firebase.messaging.FirebaseMessagingService
import android.app.NotificationManager

import android.app.NotificationChannel

import android.os.Build

import android.R
import android.annotation.SuppressLint

import android.app.PendingIntent
import android.content.Context

import android.content.Intent

import android.widget.RemoteViews
import androidx.core.app.NotificationCompat

import com.google.firebase.messaging.RemoteMessage




class MyFirebaseMessagingService:FirebaseMessagingService() {
    @SuppressLint("UnspecifiedImmutableFlag")

    override fun onMessageReceived(remoteMessage: RemoteMessage) {
        if (remoteMessage.notification != null) {

            showNotification(
                remoteMessage.notification!!.title,
                remoteMessage.notification!!.body
            )
        }
    }

    private fun getCustomDesign(
        title: String?,
        message: String?
    ): RemoteViews? {
        val remoteViews = RemoteViews(
            applicationContext.packageName,
            R.layout.simple_spinner_item
        )
        remoteViews.setTextViewText(R.id.title, title)
        remoteViews.setTextViewText(R.id.message, message)
        remoteViews.setImageViewResource(
            R.id.icon,
            R.drawable.ic_menu_compass
        )
        return remoteViews
    }
    fun showNotification(
        title: String?,
        message: String?
    ) {
        val intent = Intent(this, AddEventActivity::class.java)
        // Assign channel ID
        val channel_id = "notification_channel"
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)

        val pendingIntent = PendingIntent.getActivity(
            this, 0, intent,
            PendingIntent.FLAG_ONE_SHOT
        )

        // Create a Builder object using NotificationCompat
        // class. This will allow control over all the flags
        var builder: NotificationCompat.Builder = NotificationCompat.Builder(
            applicationContext,
            channel_id
        )
            .setSmallIcon(R.drawable.ic_menu_compass)
            .setAutoCancel(true)
            .setVibrate(
                longArrayOf(
                    1000, 1000, 1000,
                    1000, 1000
                )
            )
            .setOnlyAlertOnce(true)
            .setContentIntent(pendingIntent)

        // A customized design for the notification can be
        // set only for Android versions 4.1 and above. Thus
        // condition for the same is checked here.
        if (Build.VERSION.SDK_INT
            >= Build.VERSION_CODES.JELLY_BEAN
        ) {
            builder = builder.setContent(
                getCustomDesign(title, message)
            )
        } // If Android Version is lower than Jelly Beans,
        else {
            builder = builder.setContentTitle(title)
                .setContentText(message)
                .setSmallIcon(R.drawable.ic_menu_compass)
        }
        // Create an object of NotificationManager class to
        // notify the
        // user of events that happen in the background.
        val notificationManager = getSystemService(
            Context.NOTIFICATION_SERVICE
        ) as NotificationManager
        // Check if the Android Version is greater than Oreo
        if (Build.VERSION.SDK_INT
            >= Build.VERSION_CODES.O
        ) {
            val notificationChannel = NotificationChannel(
                channel_id, "web_app",
                NotificationManager.IMPORTANCE_HIGH
            )
            notificationManager.createNotificationChannel(
                notificationChannel
            )
        }
        notificationManager.notify(0, builder.build())
    }
}