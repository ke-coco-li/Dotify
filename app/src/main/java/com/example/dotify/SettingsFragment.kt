package com.example.dotify

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.dotify.databinding.FragmentSettingsBinding
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs


class SettingsFragment : Fragment() {

    private lateinit var binding: FragmentSettingsBinding
    private val safeArgs: SettingsFragmentArgs by navArgs()
    private val navController by lazy { findNavController() }

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?)
    : View? {

        val songObj = safeArgs.song
        val playCount = safeArgs.count

        binding = FragmentSettingsBinding.inflate(inflater)

        with(binding) {
            profileBtn.setOnClickListener{
                navController.navigate(SettingsFragmentDirections.actionGlobalProfileFragment())
            }
            aboutBtn.setOnClickListener {
                navController.navigate(SettingsFragmentDirections.actionGlobalAboutFragment())
            }
            statisticsBtn.setOnClickListener{
                navController.navigate(
                    SettingsFragmentDirections.actionGlobalStatisticsFragment(
                        songObj,
                        playCount
                    )
                )
            }
        }

        return binding.root
    }
}