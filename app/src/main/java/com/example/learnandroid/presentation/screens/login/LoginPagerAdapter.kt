package com.example.learnandroid.presentation.screens.login

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter

class LoginPagerAdapter(fragment: Fragment, private val items: Array<Fragment>) :
    FragmentStateAdapter(fragment) {

    override fun getItemCount(): Int {
        return items.count()
    }

    override fun createFragment(position: Int): Fragment {
        return items[position]
    }
}