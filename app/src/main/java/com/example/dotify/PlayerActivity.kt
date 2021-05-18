package com.example.dotify

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import android.widget.Toast
import kotlin.random.Random
import android.content.Context
import android.content.Intent
import android.view.MenuItem
import com.ericchee.songdataprovider.Song
import com.example.dotify.databinding.ActivityPlayerBinding

private const val SONG_KEY = "song"
private const val PLAY_COUNT = "count"

fun navigateToPlayerActivity(context: Context ,currentSong: Song) {
    val intent = Intent(context, PlayerActivity::class.java)
    val bundle = Bundle().apply {
        putParcelable(SONG_KEY, currentSong)
    }
    intent.putExtras(bundle)
    context.startActivity(intent)
}

class PlayerActivity : AppCompatActivity() {

    private var randomNumber = Random.nextInt(1000, 100000)
    private val dotifyApp: DotifyApplication by lazy { application as DotifyApplication }

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_main)

        if (savedInstanceState != null) {
            with(savedInstanceState) {
                randomNumber = getInt(PLAY_COUNT, -1)
            }
        }

        val binding = ActivityPlayerBinding.inflate(layoutInflater).apply { setContentView(root) }
        val plays = findViewById<TextView>(R.id.plays)
//        val playCount = plays.text.toString()
        plays.text = randomNumber.toString() + " plays"

        with(binding) {
            val currentSong = intent.getParcelableExtra<Song>(SONG_KEY)
            if (currentSong != null) {
                album.setImageResource(currentSong.largeImageID)
                song.text = currentSong.title
                singer.text = currentSong.artist
            }
            settingsBtn.setOnClickListener{
                if (currentSong != null) {
                    navigateToSettingsActivity(this@PlayerActivity, currentSong, randomNumber.toString())
                }
            }

        }

        var actionBar = supportActionBar
        actionBar?.setDisplayHomeAsUpEnabled(true)

    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.putInt(PLAY_COUNT, randomNumber)
        super.onSaveInstanceState(outState)
    }

    override fun onContextItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                finish()
                return true
            }
        }
        return super.onContextItemSelected(item)
    }

    fun prevClicked(view: View) {
        Toast.makeText(this, "Skipping to previous track", Toast.LENGTH_SHORT).show()
    }

    fun nextClicked(view: View) {
        Toast.makeText(this, "Skipping to next track", Toast.LENGTH_SHORT).show()
    }

    fun playClicked(view: View) {
        val plays = findViewById<TextView>(R.id.plays)
        val newRandom = randomNumber + 1
        randomNumber = newRandom
        plays.text = newRandom.toString() + " plays"
    }

//    fun changeClicked(view: View) {
//        val username = findViewById<TextView>(R.id.username)
//        val etUsername = findViewById<TextView>(R.id.etUsername)
//        val changeBtn = findViewById<TextView>(R.id.changeUser)
//        if (changeBtn.text == "CHANGE USER") {
//            changeBtn.text = "APPLY"
//            username.visibility = View.INVISIBLE
//            etUsername.visibility = View.VISIBLE
//            etUsername.text = username.text
//        } else {
//            changeBtn.text = "CHANGE USER"
//            username.visibility = View.VISIBLE
//            etUsername.visibility = View.INVISIBLE
//            username.text = etUsername.text
//        }
//    }
}