package com.example.learnandroid.presentation.screens.onboarding.planEstimate

import androidx.fragment.app.viewModels
import com.example.learnandroid.databinding.FragmentOnboardingGoogleFitBinding
import com.example.learnandroid.presentation.screens.base.BaseViewBindingFragment

class OnboardingGoogleFitFragment :
    BaseViewBindingFragment<FragmentOnboardingGoogleFitBinding, OnboardingGoogleFitViewModel>(
        FragmentOnboardingGoogleFitBinding::inflate
    ) {
    override val viewModel: OnboardingGoogleFitViewModel by viewModels()

    interface OnboardingPlanEstimateDelegate {
        fun didSelectEstimate()
    }

    private var delegate: OnboardingPlanEstimateDelegate? = null

    companion object {
        fun newInstance(): OnboardingGoogleFitFragment {
            return OnboardingGoogleFitFragment()
        }
    }

    fun setAction(delegate: OnboardingPlanEstimateDelegate?) {
        this.delegate = delegate
    }

    fun resetData() {
        viewModel.resetData()
    }
}