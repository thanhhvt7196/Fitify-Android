package com.example.learnandroid.presentation.screens.onboarding.goal

import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.lifecycle.viewModelScope
import com.example.learnandroid.databinding.FragmentOnboardingGoalBinding
import com.example.learnandroid.domain.models.Gender
import com.example.learnandroid.domain.models.OnboardingGoal
import com.example.learnandroid.presentation.screens.base.BaseViewBindingFragment
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class OnboardingGoalFragment :
    BaseViewBindingFragment<FragmentOnboardingGoalBinding, OnboardingGoalViewModel>(
        FragmentOnboardingGoalBinding::inflate
    ) {
    override val viewModel: OnboardingGoalViewModel by viewModels()
    private val gender = MutableStateFlow<Gender>(Gender.MALE)

    companion object {
        const val tag = "OnboardingGoalFragment"
        fun newInstance(): OnboardingGoalFragment {
            return OnboardingGoalFragment()
        }
    }

    override fun setup() {
        super.setup()
        setupUI()
        setupBinding()
    }

    private fun setupUI() {
        viewBinding.apply {
            getTonedButton.setOnClickListener {
                viewModel.setGoal(OnboardingGoal.GET_TONED)
            }

            getFitterButton.setOnClickListener {
                viewModel.setGoal(OnboardingGoal.GET_FITTER)
            }

            loseWeightButton.setOnClickListener {
                viewModel.setGoal(OnboardingGoal.LOSE_WEIGHT)
            }
        }
    }

    private fun setupBinding() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.goal.collect { goal ->
                viewBinding.getTonedButton.isSelected = goal == OnboardingGoal.GET_TONED
                viewBinding.getFitterButton.isSelected = goal == OnboardingGoal.GET_FITTER
                viewBinding.loseWeightButton.isSelected = goal == OnboardingGoal.LOSE_WEIGHT
            }
        }

        lifecycleScope.launch {
            gender.collect { gender ->
                setupButtons(gender)
            }
        }
    }

    private fun setupButtons(gender: Gender) {
        viewBinding.apply {
            getTonedButton.config(
                OnboardingGoal.GET_TONED.getDisplayName(
                    requireActivity(),
                    gender
                ), OnboardingGoal.GET_TONED.getImageResource(requireActivity(), gender)
            )

            loseWeightButton.config(
                OnboardingGoal.LOSE_WEIGHT.getDisplayName(
                    requireActivity(),
                    gender
                ), OnboardingGoal.LOSE_WEIGHT.getImageResource(requireActivity(), gender)
            )

            getFitterButton.config(
                OnboardingGoal.GET_FITTER.getDisplayName(
                    requireActivity(),
                    gender
                ), OnboardingGoal.GET_FITTER.getImageResource(requireActivity(), gender)
            )
        }
    }

    fun setGender(gender: Gender) {
       this.gender.value = gender
    }

    fun resetData() {
        viewModel.setGoal(null)
    }
}