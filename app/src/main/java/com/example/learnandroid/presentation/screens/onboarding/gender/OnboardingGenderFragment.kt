package com.example.learnandroid.presentation.screens.onboarding.gender

import androidx.fragment.app.viewModels
import com.example.learnandroid.databinding.FragmentOnboardingGenderBinding
import com.example.learnandroid.domain.models.Gender
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
        viewBinding.apply {
            maleButton.setOnClickListener {
                viewModel.setGender(Gender.MALE)
            }
            femaleButton.setOnClickListener {
                viewModel.setGender(Gender.FEMALE)
            }
        }
    }

    override suspend fun subscribeData() {
        viewModel.gender.observe(this) { gender ->
            viewBinding.maleButton.isSelected = gender == Gender.MALE
            viewBinding.femaleButton.isSelected = gender == Gender.FEMALE
        }
    }
}