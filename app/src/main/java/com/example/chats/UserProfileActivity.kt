package com.example.chats

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.chats.databinding.ProfileLayoutBinding

class UserProfileActivity : AppCompatActivity() {
    lateinit var binding: ProfileLayoutBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ProfileLayoutBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}