package com.example.learnandroid.presentation.screens.onboarding.name

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.example.learnandroid.R
import com.example.learnandroid.databinding.FragmentOnboardingNameBinding
import com.example.learnandroid.presentation.screens.base.BaseViewBindingFragment
import com.example.learnandroid.presentation.screens.onboarding.gender.OnboardingGenderFragment

class OnboardingNameFragment : BaseViewBindingFragment<FragmentOnboardingNameBinding, OnboardingNameViewModel>(FragmentOnboardingNameBinding::inflate) {
    override val viewModel: OnboardingNameViewModel by viewModels()
    override fun initView() {

    }

    override suspend fun subscribeData() {

    }

    companion object {
        const val tag = "OnboardingNameFragment"
        fun newInstance(): OnboardingNameFragment {
            return OnboardingNameFragment()
        }
    }
}