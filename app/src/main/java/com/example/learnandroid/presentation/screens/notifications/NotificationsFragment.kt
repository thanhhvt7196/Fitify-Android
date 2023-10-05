package com.example.learnandroid.presentation.screens.notifications

import com.example.learnandroid.databinding.FragmentNotificationsBinding
import com.example.learnandroid.presentation.screens.base.BaseViewBindingFragment
import com.example.learnandroid.presentation.screens.home.HomeFragment
import org.koin.androidx.viewmodel.ext.android.viewModel

class NotificationsFragment : BaseViewBindingFragment<FragmentNotificationsBinding, NotificationsViewModel>(FragmentNotificationsBinding::inflate) {
    override val viewModel: NotificationsViewModel by viewModel()

    companion object {
        fun newInstance(): NotificationsFragment {
            return NotificationsFragment()
        }
    }
    override fun initView() {
    }

    override suspend fun subscribeData() {
    }
}