package com.example.fitifyandroid.presentation.screens.main

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.fitifyandroid.presentation.screens.nutrition.NutritionFragment
import com.example.fitifyandroid.presentation.screens.plan.PlansFragment
import com.example.fitifyandroid.presentation.screens.profile.ProfileFragment
import com.example.fitifyandroid.presentation.screens.workouts.WorkoutsFragment

class MainPagerAdapter(val fragment: Fragment): FragmentStateAdapter(fragment) {
    override fun getItemCount() = 4

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> PlansFragment.newInstance()
            1 -> WorkoutsFragment.newInstance()
            2 -> NutritionFragment.newInstance()
            else -> ProfileFragment.newInstance()
        }
    }
}