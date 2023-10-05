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

    override fun initView() {
        viewBinding.apply {
            setupBottomBar(mainBottomBar)
        }
    }

    private fun setupBottomBar(bottomBar: BottomNavigationView) {
        bottomBar.setBackgroundColor(ContextCompat.getColor(requireActivity(), R.color.blue_dark))
        bottomBar.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.nutrition_tab -> {
                    replaceFragment(nutritionFragment)
                    true
                }
                R.id.workouts_tab -> {
                    replaceFragment(workoutsFragment)
                    true
                }
                R.id.plan_tab -> {
                    replaceFragment(plansFragment)
                    true
                }
                R.id.profile_tab -> {
                    replaceFragment(profileFragment)
                    true
                }
                else -> true
            }
        }
        bottomBar.selectedItemId = R.id.plan_tab
    }

    override suspend fun subscribeData() {

    }

    private fun replaceFragment(fragment: Fragment) {
        val transaction = requireActivity().supportFragmentManager.beginTransaction()
        transaction.replace(R.id.nav_host_fragment_activity_main, fragment)
        transaction.commit()
    }
}