package com.example.dotify

import android.app.Application
import com.example.dotify.manager.NotificationManager
import com.example.dotify.manager.SongManager

class DotifyApplication: Application() {

    lateinit var dataRepository: DataRepository
    lateinit var songManager: SongManager
    lateinit var notificationManager: NotificationManager

    override fun onCreate() {
        super.onCreate()
        dataRepository = DataRepository()
        songManager = SongManager()
        notificationManager = NotificationManager(this)
    }
}
