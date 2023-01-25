package com.example.chats.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.chats.R
import com.example.chats.databinding.FragmentUserChatRoomsBinding
import com.example.chats.databinding.FragmentUserContactsBinding

class UserChatRoomsFragment : Fragment() {
    private lateinit var binding: FragmentUserChatRoomsBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentUserChatRoomsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    }
}