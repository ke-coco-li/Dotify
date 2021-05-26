package com.example.dotify.manager

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.ericchee.songdataprovider.SongDataProvider
import com.example.dotify.DotifyApplication

class SongNotificationWorker (
    private val context: Context,
    workerParameters: WorkerParameters
    ): CoroutineWorker(context, workerParameters) {

        private val application by lazy { context.applicationContext as DotifyApplication }
        private val notificationManager by lazy { application.notificationManager }

        override suspend fun doWork(): Result {
            val song = SongDataProvider.getAllSongs().random()
            notificationManager.publishSongNotification(song)
            return Result.success()
        }
}
