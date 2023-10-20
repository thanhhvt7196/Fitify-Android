package com.example.learnandroid.presentation.screens.onboarding.name

import androidx.fragment.app.viewModels
import com.example.learnandroid.databinding.FragmentOnboardingNameBinding
import com.example.learnandroid.presentation.screens.base.BaseViewBindingFragment

class OnboardingNameFragment : BaseViewBindingFragment<FragmentOnboardingNameBinding, OnboardingNameViewModel>(FragmentOnboardingNameBinding::inflate) {
    override val viewModel: OnboardingNameViewModel by viewModels()

    companion object {
        const val tag = "OnboardingNameFragment"
        fun newInstance(): OnboardingNameFragment {
            return OnboardingNameFragment()
        }
    }

    override fun initView() {

    }

    override suspend fun subscribeData() {

    }
}