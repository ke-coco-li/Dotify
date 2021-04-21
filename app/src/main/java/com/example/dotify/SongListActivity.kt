package com.example.dotify

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.dotify.databinding.ActivitySongListBinding
import com.ericchee.songdataprovider.Song
import com.ericchee.songdataprovider.SongDataProvider

class SongListActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_song_list)

        val binding = ActivitySongListBinding.inflate(layoutInflater).apply { setContentView(root) }
        val rvSong = binding.songList
        val songList = SongDataProvider.getAllSongs()

        with(binding) {
            title = "All Songs"
            rvSong.adapter = SongListAdapter(songList)
        }
    }
}