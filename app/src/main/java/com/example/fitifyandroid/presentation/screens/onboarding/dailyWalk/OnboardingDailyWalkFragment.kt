package com.example.fitifyandroid.presentation.screens.onboarding.dailyWalk

import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.fitifyandroid.databinding.FragmentOnboardingDailyWalkBinding
import com.example.fitifyandroid.domain.models.DailyWalk
import com.example.fitifyandroid.presentation.screens.base.BaseViewBindingFragment
import kotlinx.coroutines.launch

class OnboardingDailyWalkFragment :
    BaseViewBindingFragment<FragmentOnboardingDailyWalkBinding, OnboardingDailyWalkViewModel>(
        FragmentOnboardingDailyWalkBinding::inflate
    ) {
    override val viewModel: OnboardingDailyWalkViewModel by viewModels()

    interface OnboardingDailyWalkDelegate {
        fun didSelectDailyWalk(dailyWalk: DailyWalk)
    }

    private var delegate: OnboardingDailyWalkDelegate? = null

    companion object {
        fun newInstance(): OnboardingDailyWalkFragment {
            return OnboardingDailyWalkFragment()
        }
    }

    fun setAction(delegate: OnboardingDailyWalkDelegate) {
        this.delegate = delegate
    }

    override fun setup() {
        super.setup()
        setupUI()
        setupBinding()
    }

    private fun setupUI() {
        viewBinding.apply {
            moreThanTwoButton.isSelected = false
            oneToTwoButton.isSelected = false
            lessThanOneButton.isSelected = false

            moreThanTwoButton.config(DailyWalk.MORE_THAN_TWO_HOURS.getTitle(requireActivity()))
            moreThanTwoButton.setOnClickListener {
                viewModel.setDailyWalk(DailyWalk.MORE_THAN_TWO_HOURS)
            }

            oneToTwoButton.config(DailyWalk.ONE_TO_TWO_HOURS.getTitle(requireActivity()))
            oneToTwoButton.setOnClickListener {
                viewModel.setDailyWalk(DailyWalk.ONE_TO_TWO_HOURS)
            }

            lessThanOneButton.config(DailyWalk.LESS_THAN_AN_HOUR.getTitle(requireActivity()))
            lessThanOneButton.setOnClickListener {
                viewModel.setDailyWalk(DailyWalk.LESS_THAN_AN_HOUR)
            }
        }
    }

    private fun setupBinding() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.dailyWalk.collect { dailyWalk ->
                viewBinding.apply {
                    moreThanTwoButton.isSelected = dailyWalk == DailyWalk.MORE_THAN_TWO_HOURS
                    oneToTwoButton.isSelected = dailyWalk == DailyWalk.ONE_TO_TWO_HOURS
                    lessThanOneButton.isSelected = dailyWalk == DailyWalk.LESS_THAN_AN_HOUR
                }
                dailyWalk?.let {
                    delegate?.didSelectDailyWalk(it)
                }
            }
        }
    }

    fun resetData() {
        viewModel.setDailyWalk(null)
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