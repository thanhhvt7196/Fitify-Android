package com.example.fitifyandroid.presentation.screens.onboarding.googleFit

import androidx.fragment.app.viewModels
import com.example.fitifyandroid.databinding.FragmentOnboardingGoogleFitBinding
import com.example.fitifyandroid.presentation.screens.base.BaseViewBindingFragment

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