package com.example.dotify

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.dotify.databinding.FragmentProfileBinding
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.launch
import coil.load

class ProfileFragment : Fragment() {

    private val dotifyApp: DotifyApplication by lazy { requireActivity().application as DotifyApplication }
    private val dataRepository by lazy { dotifyApp.dataRepository }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentProfileBinding.inflate(inflater)
        with(binding){
            swiperefresh.setOnRefreshListener {
                lifecycleScope.launch {
                    kotlin.runCatching {
                        val user = dataRepository.fetchUser()
                        binding.profilePic.load(user.profilePicURL)
                        binding.profileUsername.text = user.username
                        binding.firstName.text = "First Name: " + user.firstName
                        binding.lastName.text = "Last Name: " + user.lastName
                        binding.hasNose.text = "Has Nose: " + user.hasNose
                        profilePic.visibility = View.VISIBLE
                        profileUsername.visibility = View.VISIBLE
                        firstName.visibility = View.VISIBLE
                        lastName.visibility = View.VISIBLE
                        hasNose.visibility = View.VISIBLE
                        errorMsg.visibility = View.GONE
                    }.onFailure {
                        binding.errorMsg.visibility = View.VISIBLE
                        profilePic.visibility = View.GONE
                        profileUsername.visibility = View.GONE
                        firstName.visibility = View.GONE
                        lastName.visibility = View.GONE
                        hasNose.visibility = View.GONE
                    }
                }
                swiperefresh.isRefreshing = false
            }

        }
        return binding.root
    }
}