package com.example.learnandroid.ui.screens.notifications

import com.example.learnandroid.databinding.FragmentNotificationsBinding
import com.example.learnandroid.ui.screens.base.BaseViewBindingFragment
import org.koin.androidx.viewmodel.ext.android.viewModel

class NotificationsFragment : BaseViewBindingFragment<FragmentNotificationsBinding, NotificationsViewModel>(FragmentNotificationsBinding::inflate) {
    override val viewModel: NotificationsViewModel by viewModel()
    override fun initView() {
    }

    override suspend fun subscribeData() {
    }
}