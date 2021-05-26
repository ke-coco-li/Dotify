package com.example.dotify

import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.dotify.databinding.ActivitySongListBinding
import com.ericchee.songdataprovider.Song
import com.ericchee.songdataprovider.SongDataProvider

private const val CURRENT_SONG = "currentSong"

class SongListActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySongListBinding
    private lateinit var currentSong: Song
    private val dotifyApp: DotifyApplication by lazy { application as DotifyApplication }
//    private val dataRepository by lazy { dotifyApp.dataRepository }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySongListBinding.inflate(layoutInflater).apply { setContentView(root) }
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
//          adapter.onSongClickListener = {
//              Toast.makeText(this@SongListActivity, "song is clicked", Toast.LENGTH_SHORT).show()
//          }

            if (savedInstanceState != null) {
                with(savedInstanceState) {
                    currentSong = this.getParcelable(CURRENT_SONG)!!
                    toggleMiniplayer(currentSong)
                }
            }
            adapter.onSongClickListener = { currentSong: Song ->
                this@SongListActivity.currentSong = currentSong  // update local song object
                toggleMiniplayer(currentSong)
                dotifyApp.songManager.onSongSelected(currentSong)
            }
        }

    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.putParcelable(CURRENT_SONG, currentSong)
        super.onSaveInstanceState(outState)
    }

    private fun toggleMiniplayer(currentSong: Song) {
        binding.miniplayer.visibility = View.VISIBLE
        binding.miniSongTitle.text = "${currentSong.title} - ${currentSong.artist}"
        binding.miniplayer.setOnClickListener {
            navigateToPlayerActivity(this@SongListActivity, currentSong)
        }
    }

}

