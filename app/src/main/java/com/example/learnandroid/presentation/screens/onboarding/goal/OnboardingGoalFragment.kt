package com.example.learnandroid.presentation.screens.onboarding.goal

import androidx.fragment.app.viewModels
import com.example.learnandroid.databinding.FragmentOnboardingGoalBinding
import com.example.learnandroid.presentation.screens.base.BaseViewBindingFragment

class OnboardingGoalFragment : BaseViewBindingFragment<FragmentOnboardingGoalBinding, OnboardingGoalViewModel>(FragmentOnboardingGoalBinding::inflate) {
    override val viewModel: OnboardingGoalViewModel by viewModels()

    companion object {
        const val tag = "OnboardingGoalFragment"
        fun newInstance(): OnboardingGoalFragment {
            return OnboardingGoalFragment()
        }
    }

    override fun initView() {

    }

    override suspend fun subscribeData() {

    }
}