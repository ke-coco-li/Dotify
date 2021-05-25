package com.example.dotify.manager

import android.content.Context
import androidx.work.*
import java.util.concurrent.TimeUnit

const val NEW_SONG_TAG = "NEW_SONG_TAG"

class SongNotificationManager(context: Context) {

    private val workManager: WorkManager = WorkManager.getInstance(context)

    fun getSongPeriodically() {
        if(isWorkRunningByTag()) {
            workManager.cancelAllWorkByTag(NEW_SONG_TAG)
        }

        val request = PeriodicWorkRequestBuilder<SongNotificationWorker>(20, TimeUnit.MINUTES)
            .setInitialDelay(5, TimeUnit.SECONDS)
            .setConstraints(
                Constraints.Builder()
                    .setRequiredNetworkType(NetworkType.CONNECTED)
                    .build()
            )
            .addTag(NEW_SONG_TAG)
            .build()

        workManager.enqueue(request)
    }

    private fun isWorkRunningByTag(): Boolean {
        return workManager.getWorkInfosByTag(NEW_SONG_TAG).get().any {
            when(it.state) {
                WorkInfo.State.RUNNING,
                WorkInfo.State.ENQUEUED -> true
                else -> false
            }
        }
    }

}