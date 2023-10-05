package com.example.learnandroid.presentation.screens.profile

import com.example.learnandroid.databinding.FragmentProfileBinding
import com.example.learnandroid.presentation.screens.base.BaseViewBindingFragment
import org.koin.androidx.viewmodel.ext.android.viewModel

class ProfileFragment : BaseViewBindingFragment<FragmentProfileBinding, ProfileViewModel>(FragmentProfileBinding::inflate) {

    override val viewModel: ProfileViewModel by viewModel()

    companion object {
        fun newInstance(): ProfileFragment {
            return ProfileFragment()
        }
    }

    override fun initView() {

    }

    override suspend fun subscribeData() {
    }
}