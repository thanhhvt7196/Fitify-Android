package com.example.learnandroid.presentation.screens.onboarding.kneePain

import androidx.fragment.app.viewModels
import com.example.learnandroid.R
import com.example.learnandroid.databinding.FragmentOnboardingKneePainBinding
import com.example.learnandroid.domain.models.OnboardingGoal
import com.example.learnandroid.presentation.screens.base.BaseViewBindingFragment

class OnboardingKneePainFragment : BaseViewBindingFragment<FragmentOnboardingKneePainBinding, OnboardingKneePainViewModel>(FragmentOnboardingKneePainBinding::inflate) {
    override val viewModel: OnboardingKneePainViewModel by viewModels()

    interface OnboardingKneePainDelegate {
        fun didSelectGoal(goal: OnboardingGoal)
    }

    private var delegate: OnboardingKneePainDelegate? = null

    companion object {
        const val tag = "OnboardingKneePainFragment"
        fun newInstance(): OnboardingKneePainFragment {
            return OnboardingKneePainFragment()
        }
    }

    override fun setup() {
        super.setup()
        setupUI()
        setupBinding()
    }

    private fun setupUI() {

    }

    private fun setupBinding() {

    }
}