package com.example.learnandroid.presentation.screens.onboarding.goal

import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
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

    interface OnboardingGoalDelegate {
        fun didSelectGoal(goal: OnboardingGoal)
    }

    private var delegate: OnboardingGoalDelegate? = null

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
            getTonedButton.isSelected = false
            getTonedButton.setOnClickListener {
                viewModel.setGoal(OnboardingGoal.GET_TONED)
            }

            loseWeightButton.isSelected = false
            loseWeightButton.setOnClickListener {
                viewModel.setGoal(OnboardingGoal.LOSE_WEIGHT)
                viewModel.setGoal(OnboardingGoal.LOSE_WEIGHT)
            }
        }
    }

    private fun setupBinding() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.goal.collect { goal ->
                viewBinding.getTonedButton.isSelected = goal == OnboardingGoal.GET_TONED
                viewBinding.loseWeightButton.isSelected = goal == OnboardingGoal.LOSE_WEIGHT
                goal?.let {
                    delegate?.didSelectGoal(it)
                }
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
        }
    }

    fun setGender(gender: Gender) {
       this.gender.value = gender
    }

    fun resetData() {
        viewModel.setGoal(null)
    }

    fun setAction(delegate: OnboardingGoalDelegate) {
        this.delegate = delegate
    }
}