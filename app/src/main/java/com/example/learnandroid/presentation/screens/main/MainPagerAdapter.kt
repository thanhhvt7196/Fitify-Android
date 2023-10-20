package com.example.learnandroid.presentation.screens.main

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.learnandroid.presentation.screens.nutrition.NutritionFragment
import com.example.learnandroid.presentation.screens.plan.PlansFragment
import com.example.learnandroid.presentation.screens.profile.ProfileFragment
import com.example.learnandroid.presentation.screens.workouts.WorkoutsFragment

class MainPagerAdapter(val fragment: Fragment): FragmentStateAdapter(fragment) {
    override fun getItemCount() = 4

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> PlansFragment()
            1 -> WorkoutsFragment()
            2 -> NutritionFragment()
            else -> ProfileFragment()
        }
    }
}