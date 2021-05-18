package com.example.dotify

import com.ericchee.songdataprovider.Song

class SongManager {
    var selectedSong: Song? = null
        private set

    fun onSongSelected(song: Song) {
        selectedSong = song
    }
}