package com.example.learnandroid.presentation.screens.onboarding.weight

import android.content.Context
import androidx.fragment.app.viewModels
import com.example.learnandroid.R
import com.example.learnandroid.databinding.FragmentOnboardingWeightBinding
import com.example.learnandroid.presentation.screens.base.BaseViewBindingFragment
import com.example.learnandroid.utils.extensions.focus
import com.example.learnandroid.utils.extensions.unFocus


class OnboardingWeightFragment() : BaseViewBindingFragment<FragmentOnboardingWeightBinding, OnboardingWeightViewModel>(FragmentOnboardingWeightBinding::inflate) {
    override val viewModel: OnboardingWeightViewModel by viewModels()

    companion object {
        const val tag = "OnboardingWeightFragment"
        fun currentWeightNewInstance(): OnboardingWeightFragment {
            return OnboardingWeightFragment()
        }

        fun targetWeightNewInstance(): OnboardingWeightFragment {
            return OnboardingWeightFragment()
        }
    }

    interface OnboardingWeightDelegate {
        fun didSelectWeight(weight: Float)
    }

    private var delegate: OnboardingWeightDelegate? = null

    fun setAction(delegate: OnboardingWeightDelegate) {
        this.delegate = delegate
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

    fun resetData() {
        viewModel.setWeight(null)
    }

    override fun onResume() {
        super.onResume()
        viewBinding.weightTextField.focus(requireActivity())
    }

    override fun onPause() {
        super.onPause()
        viewBinding.weightTextField.unFocus(requireActivity())
    }

    override fun onDestroyView() {
        super.onDestroyView()
        delegate = null
    }

    override fun onDestroy() {
        super.onDestroy()
        delegate = null
    }
}