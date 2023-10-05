package com.example.learnandroid.presentation.screens.dashboard

import com.example.learnandroid.databinding.FragmentDashboardBinding
import com.example.learnandroid.presentation.screens.base.BaseViewBindingFragment
import com.example.learnandroid.presentation.screens.home.HomeFragment
import org.koin.androidx.viewmodel.ext.android.viewModel

class DashboardFragment : BaseViewBindingFragment<FragmentDashboardBinding, DashboardViewModel>(FragmentDashboardBinding::inflate) {

    override val viewModel: DashboardViewModel by viewModel()

    companion object {
        fun newInstance(): DashboardFragment {
            return DashboardFragment()
        }
    }

    override fun initView() {

    }

    override suspend fun subscribeData() {
    }
}