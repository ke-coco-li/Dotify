package com.example.dotify.manager

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.ericchee.songdataprovider.Song
import com.example.dotify.PlayerActivity
import com.example.dotify.R
import kotlin.random.Random
import com.example.dotify.SONG_KEY

private const val NEW_SONG_CHANNEL_ID = "NEW_SONG_CHANNEL_ID"

class NotificationManager(
    private val context: Context
) {
    private val notificationManager = NotificationManagerCompat.from(context)

    init {
        initNotificationChannels()
    }

    fun publishSongNotification(song: Song) {
        val intent = Intent(context, PlayerActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            putExtra(SONG_KEY, song)
        }

        val pendingIntent: PendingIntent = PendingIntent.getActivity(context, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT)

        val builder = NotificationCompat.Builder(context, NEW_SONG_CHANNEL_ID)
            .setSmallIcon(R.drawable.ic_baseline_music_note_24)
            .setContentTitle("${song.artist} just released a new song!!!")
            .setContentText("Listening to ${song.title} now on Dotify")
            .setContentIntent(pendingIntent)
            .setAutoCancel(true)
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)

        with(notificationManager) {
            val notificationId = Random.nextInt()
            notify(notificationId, builder.build())
        }

    }

    private fun initNotificationChannels() {
        initNewSongChannel()
    }

    private fun initNewSongChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val name = "New Uploaded Music"
            val descriptionText = "This is a channel to get information about newly released music."
            val importance = NotificationManager.IMPORTANCE_DEFAULT
            val channel = NotificationChannel(NEW_SONG_CHANNEL_ID, name, importance).apply {
                description = descriptionText
            }
            notificationManager.createNotificationChannel(channel)
        }
    }

}