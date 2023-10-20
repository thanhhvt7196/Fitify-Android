package com.example.learnandroid.presentation.screens.onboarding.gender

import androidx.fragment.app.viewModels
import com.example.learnandroid.databinding.FragmentOnboardingGenderBinding
import com.example.learnandroid.presentation.screens.base.BaseViewBindingFragment

class OnboardingGenderFragment : BaseViewBindingFragment<FragmentOnboardingGenderBinding, OnboardingGenderViewModel>(FragmentOnboardingGenderBinding::inflate) {
    override val viewModel: OnboardingGenderViewModel by viewModels()

    companion object {
        const val tag = "OnboardingGenderFragment"
        fun newInstance(): OnboardingGenderFragment {
            return OnboardingGenderFragment()
        }
    }

    override fun initView() {

    }

    override suspend fun subscribeData() {

    }
}