package com.example.dotify

import android.app.Application

class DotifyApplication: Application() {

    lateinit var dataRepository: DataRepository
    lateinit var songManager: SongManager

    override fun onCreate() {
        super.onCreate()
        dataRepository = DataRepository()
        songManager = SongManager()
    }
}
