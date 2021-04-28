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

    private val navController by lazy { findNavController() }

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentSettingsBinding.inflate(inflater)

        with(binding) {
            profileBtn.setOnClickListener{
                navController.navigate(R.id.profileFragment)
            }

            aboutBtn.setOnClickListener{
                navController.navigate(R.id.aboutFragment)
            }

            statisticsBtn.setOnClickListener{
                navController.navigate(R.id.statisticsFragment)
            }

        }

        return binding.root
    }

}