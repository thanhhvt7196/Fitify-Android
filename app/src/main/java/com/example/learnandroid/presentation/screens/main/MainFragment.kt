package com.example.learnandroid.presentation.screens.main

import android.content.res.ColorStateList
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.example.learnandroid.R
import com.example.learnandroid.databinding.FragmentMainBinding
import com.example.learnandroid.presentation.screens.base.BaseViewBindingFragment
import com.example.learnandroid.presentation.screens.dashboard.DashboardFragment
import com.example.learnandroid.presentation.screens.home.HomeFragment
import com.example.learnandroid.presentation.screens.notifications.NotificationsFragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.snackbar.BaseTransientBottomBar
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainFragment :
    BaseViewBindingFragment<FragmentMainBinding, MainViewModel>(FragmentMainBinding::inflate) {
    override val viewModel: MainViewModel by viewModel()
    private val homeFragment = HomeFragment.newInstance()
    private val notificationFragment = NotificationsFragment.newInstance()
    private val dashboardFragment = DashboardFragment.newInstance()

    override fun initView() {
        viewBinding.apply {
            setupBottomBar(mainBottomBar)
        }
    }

    private fun setupBottomBar(bottomBar: BottomNavigationView) {
        bottomBar.setBackgroundColor(ContextCompat.getColor(requireActivity(), R.color.blue_dark))
        bottomBar.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.navigation_home -> {
                    replaceFragment(homeFragment)
                    true
                }
                R.id.navigation_notifications -> {
                    replaceFragment(notificationFragment)
                    true
                }
                R.id.navigation_dashboard -> {
                    replaceFragment(dashboardFragment)
                    true
                }
                else -> true
            }
        }
        bottomBar.selectedItemId = R.id.navigation_home
    }

    override suspend fun subscribeData() {

    }

    private fun replaceFragment(fragment: Fragment) {
        val transaction = requireActivity().supportFragmentManager.beginTransaction()
        transaction.replace(R.id.nav_host_fragment_activity_main, fragment)
        transaction.commit()
    }
}