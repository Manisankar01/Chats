package com.example.chats

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.chats.fragments.SignInFragment
import com.example.chats.databinding.ActivityMainBinding


open class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityMainBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        val fragment = SignInFragment()
        val fragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.add(binding.flContainer.id, fragment, fragment.javaClass.name)
            .addToBackStack(null).commit()
    }
}