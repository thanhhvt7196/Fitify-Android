package com.example.learnandroid.presentation.screens.login

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.learnandroid.presentation.screens.onboarding.gender.OnboardingGenderFragment
import com.example.learnandroid.presentation.screens.onboarding.name.OnboardingNameFragment

class LoginPagerAdapter(fragment: Fragment): FragmentStateAdapter(fragment) {
    override fun getItemCount(): Int {
        return 2
    }

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> OnboardingGenderFragment.newInstance()
            else -> OnboardingNameFragment.newInstance()
        }
    }
}