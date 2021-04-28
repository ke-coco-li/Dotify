package com.example.dotify

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.example.dotify.databinding.FragmentStatisticsBinding

class StatisticsFragment : Fragment() {
    private lateinit var binding: FragmentStatisticsBinding
    private val safeArgs: StatisticsFragmentArgs by navArgs()

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        binding = FragmentStatisticsBinding.inflate(inflater)

        val currentSong = safeArgs.songObj
        val songCount = safeArgs.playCount

        with(binding) {
            statAlbum.setImageResource(currentSong.largeImageID)
            statPlayCount.text = songCount
        }

        return binding.root
    }
}
