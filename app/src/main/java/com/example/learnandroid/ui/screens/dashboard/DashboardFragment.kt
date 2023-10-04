package com.example.learnandroid.ui.screens.dashboard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.learnandroid.databinding.FragmentDashboardBinding
import com.example.learnandroid.ui.screens.base.BaseViewBindingFragment
import org.koin.androidx.viewmodel.ext.android.viewModel

class DashboardFragment : BaseViewBindingFragment<FragmentDashboardBinding, DashboardViewModel>(FragmentDashboardBinding::inflate) {

    override val viewModel: DashboardViewModel by viewModel()

    override fun initView() {

    }

    override suspend fun subscribeData() {
    }
}