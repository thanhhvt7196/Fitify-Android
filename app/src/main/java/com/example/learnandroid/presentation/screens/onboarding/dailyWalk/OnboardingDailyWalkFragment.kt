package com.example.learnandroid.presentation.screens.onboarding.dailyWalk

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.learnandroid.R
import com.example.learnandroid.databinding.FragmentOnboardingDailyWalkBinding
import com.example.learnandroid.domain.models.DailyWalk
import com.example.learnandroid.domain.models.PushUp
import com.example.learnandroid.presentation.screens.base.BaseViewBindingFragment
import com.example.learnandroid.presentation.screens.onboarding.pushup.OnboardingPushUpFragment
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
        const val tag = "OnboardingDailyWalkFragment"
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

            moreThanTwoButton.config(DailyWalk.MORE_THAN_TWO_HOURs.getTitle(requireActivity()))
            moreThanTwoButton.setOnClickListener {
                viewModel.setDailyWalk(DailyWalk.MORE_THAN_TWO_HOURs)
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
                    moreThanTwoButton.isSelected = dailyWalk == DailyWalk.MORE_THAN_TWO_HOURs
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