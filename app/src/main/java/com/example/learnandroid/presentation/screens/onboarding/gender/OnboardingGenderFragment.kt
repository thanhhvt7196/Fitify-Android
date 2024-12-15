package com.example.learnandroid.presentation.screens.onboarding.gender

import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.viewModelScope
import com.example.learnandroid.databinding.FragmentOnboardingGenderBinding
import com.example.learnandroid.domain.models.Gender
import com.example.learnandroid.presentation.screens.base.BaseViewBindingFragment
import com.example.learnandroid.presentation.screens.loginBottomSheet.LoginBottomSheetDelegate
import com.example.learnandroid.presentation.screens.loginBottomSheet.LoginBottomSheetFragment
import com.example.learnandroid.presentation.screens.loginBottomSheet.LoginType
import kotlinx.coroutines.launch

class OnboardingGenderFragment :
    BaseViewBindingFragment<FragmentOnboardingGenderBinding, OnboardingGenderViewModel>(
        FragmentOnboardingGenderBinding::inflate
    ) {

    interface OnboardingGenderDelegate {
        fun didSelectGender(gender: Gender)
        fun didSelectLoginType(loginType: LoginType)
    }

    private var delegate: OnboardingGenderDelegate? = null

    override val viewModel: OnboardingGenderViewModel by viewModels()

    companion object {
        fun newInstance(): OnboardingGenderFragment {
            return OnboardingGenderFragment()
        }
    }

    fun setAction(delegate: OnboardingGenderDelegate) {
        this.delegate = delegate
    }

    override fun setup() {
        super.setup()
        setupUI()
        setupBinding()
    }

    private fun setupUI() {
        viewBinding.apply {
            maleButton.setOnClickListener {
                viewLifecycleOwner.lifecycleScope.launch {
                    viewModel.setGender(Gender.MALE)
                    delegate?.didSelectGender(Gender.MALE)
                }
            }

            femaleButton.setOnClickListener {
                viewModel.viewModelScope.launch {
                    viewModel.setGender(Gender.FEMALE)
                    delegate?.didSelectGender(Gender.FEMALE)
                }
            }

            loginButton.setOnClickListener {
                val loginBottomSheet = LoginBottomSheetFragment.newInstance()

                val delegate = object : LoginBottomSheetDelegate {
                    override fun didSelectLoginType(type: LoginType) {
                        delegate?.didSelectLoginType(type)
                    }
                }

                loginBottomSheet.setAction(delegate)

                loginBottomSheet.show(
                    requireActivity().supportFragmentManager,
                    loginBottomSheet.tag
                )
            }
        }
    }

    private fun setupBinding() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.gender.collect { gender ->
                viewBinding.maleButton.isSelected = gender == Gender.MALE
                viewBinding.femaleButton.isSelected = gender == Gender.FEMALE
            }
        }
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