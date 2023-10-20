package com.example.learnandroid.presentation.screens.main

import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.example.learnandroid.R
import com.example.learnandroid.databinding.FragmentMainBinding
import com.example.learnandroid.presentation.screens.base.BaseViewBindingFragment
import com.example.learnandroid.presentation.screens.profile.ProfileFragment
import com.example.learnandroid.presentation.screens.plan.PlansFragment
import com.example.learnandroid.presentation.screens.nutrition.NutritionFragment
import com.example.learnandroid.presentation.screens.workouts.WorkoutsFragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainFragment :
    BaseViewBindingFragment<FragmentMainBinding, MainViewModel>(FragmentMainBinding::inflate) {
    override val viewModel: MainViewModel by viewModel()
    private val plansFragment = PlansFragment.newInstance()
    private val nutritionFragment = NutritionFragment.newInstance()
    private val profileFragment = ProfileFragment.newInstance()
    private val workoutsFragment = WorkoutsFragment.newInstance()

    private lateinit var mainPagerAdapter: MainPagerAdapter

    override fun initView() {
        viewBinding.apply {
            setupBottomBar(mainBottomBar)
            mainPagerAdapter = MainPagerAdapter(this@MainFragment)
            mainViewPager.apply {
                isUserInputEnabled = false
                adapter = mainPagerAdapter
                offscreenPageLimit = 3
            }
        }
    }

    private fun setupBottomBar(bottomBar: BottomNavigationView) {
        bottomBar.setBackgroundColor(ContextCompat.getColor(requireActivity(), R.color.blue_dark))
        bottomBar.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.nutrition_tab -> {
                    viewBinding.mainViewPager.setCurrentItem(2, false)
                    true
                }
                R.id.workouts_tab -> {
                    viewBinding.mainViewPager.setCurrentItem(1, false)
                    true
                }
                R.id.plan_tab -> {
                    viewBinding.mainViewPager.setCurrentItem(0, false)
                    true
                }
                R.id.profile_tab -> {
                    viewBinding.mainViewPager.setCurrentItem(3, false)
                    true
                }
                else -> true
            }
        }
        bottomBar.selectedItemId = R.id.plan_tab
    }

    override suspend fun subscribeData() {

    }
}