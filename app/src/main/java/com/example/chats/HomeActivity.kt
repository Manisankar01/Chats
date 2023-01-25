package com.example.chats

import android.content.res.Resources
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.chats.databinding.HomeLayoutBinding
import com.google.android.material.tabs.TabLayoutMediator

class HomeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = HomeLayoutBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.viewPager.adapter = PageAdapter(this)

        TabLayoutMediator(binding.tabLayout, binding.viewPager) { tab, position ->
            tab.text = when (position) {
                0 -> {
                    "Chats"
                }
                1 -> {
                    "Contacts"
                }
                2 -> {
                    "Profile"
                }
                else -> {
                    throw Resources.NotFoundException("position Not Found")
                }
            }

        }.attach()

    }
}