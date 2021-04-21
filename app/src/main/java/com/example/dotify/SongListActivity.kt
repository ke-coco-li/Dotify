package com.example.dotify

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.dotify.databinding.ActivitySongListBinding
import com.ericchee.songdataprovider.Song
import com.ericchee.songdataprovider.SongDataProvider

class SongListActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_song_list)

        val binding = ActivitySongListBinding.inflate(layoutInflater).apply { setContentView(root) }
        val rvSong = binding.songList
        val songList = SongDataProvider.getAllSongs()

        with(binding) {
            title = "All Songs"
            val adapter = SongListAdapter(songList)
            rvSong.adapter = adapter

            btnShuffle.setOnClickListener {
                val newSongs = songList.shuffled()
                adapter.updateSongs(newSongs)
            }

//            adapter.onSongClickListener = {
//                Toast.makeText(this@SongListActivity, "song is clicked", Toast.LENGTH_SHORT).show()
//            }

            adapter.onSongClickListener = { currentSong: Song ->
                miniplayer.visibility = View.VISIBLE
                miniSongTitle.text = "${currentSong.title} - ${currentSong.artist}"
                miniplayer.setOnClickListener { navigateToPlayerActivity(this@SongListActivity, currentSong) }
            }

        }
    }
}