package com.example.chats

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.chats.fragments.UserChatRoomsFragment
import com.example.chats.fragments.UserContactsFragment
import com.example.chats.fragments.UserProfileFragment

class PageAdapter(fm: FragmentActivity) : FragmentStateAdapter(fm) {
    override fun getItemCount(): Int {
        return 3
    }

    override fun createFragment(position: Int): Fragment {
        when (position) {
            0 -> (return UserChatRoomsFragment())
            1 -> (return UserContactsFragment())
            2 -> (return UserProfileFragment())
            else -> (return UserChatRoomsFragment())


        }
    }

}