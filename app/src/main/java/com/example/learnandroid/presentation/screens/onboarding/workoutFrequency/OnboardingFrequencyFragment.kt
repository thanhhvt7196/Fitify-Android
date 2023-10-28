package com.example.learnandroid.presentation.screens.onboarding.workoutFrequency

import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.learnandroid.databinding.FragmentOnboardingFrequencyBinding
import com.example.learnandroid.domain.models.WorkoutFrequency
import com.example.learnandroid.presentation.screens.base.BaseViewBindingFragment
import kotlinx.coroutines.launch

class OnboardingFrequencyFragment :
    BaseViewBindingFragment<FragmentOnboardingFrequencyBinding, OnboardingFrequencyViewModel>(
        FragmentOnboardingFrequencyBinding::inflate
    ) {
    override val viewModel: OnboardingFrequencyViewModel by viewModels()

    interface OnboardingFrequencyDelegate {
        fun didSelectFrequency(frequency: WorkoutFrequency)
    }

    private var delegate: OnboardingFrequencyDelegate? = null

    companion object {
        const val tag = "OnboardingFrequencyFragment"
        fun newInstance(): OnboardingFrequencyFragment {
            return OnboardingFrequencyFragment()
        }
    }

    fun setAction(delegate: OnboardingFrequencyDelegate) {
        this.delegate = delegate
    }

    override fun setup() {
        super.setup()
        setupUI()
        setupBinding()
    }

    private fun setupUI() {
        viewBinding.apply {
            moreThanFourButton.isSelected = false
            threeToFourButton.isSelected = false
            oneToTwoButton.isSelected = false
            neverButton.isSelected = false

            moreThanFourButton.config(WorkoutFrequency.MORE_THAN_FOUR.getTitle(requireActivity()))
            moreThanFourButton.setOnClickListener {
                viewModel.setFrequency(WorkoutFrequency.MORE_THAN_FOUR)
            }

            threeToFourButton.config(WorkoutFrequency.THREE_OR_FOUR.getTitle(requireActivity()))
            threeToFourButton.setOnClickListener {
                viewModel.setFrequency(WorkoutFrequency.THREE_OR_FOUR)
            }

            oneToTwoButton.config(WorkoutFrequency.ONE_OR_TWO.getTitle(requireActivity()))
            oneToTwoButton.setOnClickListener {
                viewModel.setFrequency(WorkoutFrequency.ONE_OR_TWO)
            }

            neverButton.config(WorkoutFrequency.NEVER.getTitle(requireActivity()))
            neverButton.setOnClickListener {
                viewModel.setFrequency(WorkoutFrequency.NEVER)
            }
        }
    }

    private fun setupBinding() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.frequency.collect { frequency ->
                viewBinding.apply {
                    moreThanFourButton.isSelected = frequency == WorkoutFrequency.MORE_THAN_FOUR
                    threeToFourButton.isSelected = frequency == WorkoutFrequency.THREE_OR_FOUR
                    oneToTwoButton.isSelected = frequency == WorkoutFrequency.ONE_OR_TWO
                    neverButton.isSelected = frequency == WorkoutFrequency.NEVER
                }
                frequency?.let {
                    delegate?.didSelectFrequency(it)
                }
            }
        }
    }

    fun resetData() {
        viewModel.setFrequency(null)
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