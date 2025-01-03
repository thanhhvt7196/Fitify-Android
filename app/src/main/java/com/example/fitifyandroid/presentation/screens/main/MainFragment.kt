package com.example.fitifyandroid.presentation.screens.main

import androidx.core.content.ContextCompat
import com.example.fitifyandroid.R
import com.example.fitifyandroid.databinding.FragmentMainBinding
import com.example.fitifyandroid.presentation.screens.base.BaseViewBindingFragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainFragment :
    BaseViewBindingFragment<FragmentMainBinding, MainViewModel>(FragmentMainBinding::inflate) {
    override val viewModel: MainViewModel by viewModel()

    private lateinit var mainPagerAdapter: MainPagerAdapter

    companion object {
        fun newInstance(): MainFragment {
            return MainFragment()
        }
    }

    override fun setup() {
        super.setup()
        setupUI()
    }

    private fun setupUI() {
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
}