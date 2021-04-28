package com.example.dotify

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.ericchee.songdataprovider.Song
import com.example.dotify.databinding.ActivitySettingsBinding
import androidx.navigation.findNavController
import androidx.navigation.ui.setupActionBarWithNavController

private const val SONG = "song"
private const val PLAY_COUNT = "count"

fun navigateToSettingsActivity(context: Context, songObj: Song, playCount : String) {
    val intent = Intent(context, SettingsActivity::class.java)
    val bundle = Bundle().apply {
        putParcelable(SONG, songObj)
        putString(PLAY_COUNT, playCount)
    }
    intent.putExtras(bundle)
    context.startActivity(intent)
}

class SettingsActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySettingsBinding
    private val navController by lazy { findNavController(R.id.navHost) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySettingsBinding.inflate(layoutInflater).apply { setContentView(root) }

        val extras: Bundle? = intent.extras
        if (extras != null) {
            navController.setGraph(R.navigation.nav_graph, intent.extras)
        }

        setupActionBarWithNavController(navController)
    }

    override fun onSupportNavigateUp(): Boolean {
        navController.navigateUp()
        return true
    }
}