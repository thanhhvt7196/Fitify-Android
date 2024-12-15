package com.example.learnandroid.presentation.screens.onboarding.planPace

import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.learnandroid.databinding.FragmentOnboardingPlanPaceBinding
import com.example.learnandroid.domain.models.PlanPace
import com.example.learnandroid.presentation.screens.base.BaseViewBindingFragment
import kotlinx.coroutines.launch

class OnboardingPlanPaceFragment :
    BaseViewBindingFragment<FragmentOnboardingPlanPaceBinding, OnboardingPlanPaceViewModel>(
        FragmentOnboardingPlanPaceBinding::inflate
    ) {
    override val viewModel: OnboardingPlanPaceViewModel by viewModels()

    interface OnboardingPlanPaceDelegate {
        fun didSelectPlanPace(planPace: PlanPace)
    }

    private var delegate: OnboardingPlanPaceDelegate? = null

    companion object {
        fun newInstance(): OnboardingPlanPaceFragment {
            return OnboardingPlanPaceFragment()
        }
    }

    fun setAction(delegate: OnboardingPlanPaceDelegate) {
        this.delegate = delegate
    }

    override fun setup() {
        super.setup()
        setupUI()
        setupBinding()
    }

    private fun setupUI() {
        viewBinding.apply {
            fastPaceButton.isSelected = false
            balancedPaceButton.isSelected = false
            slowPaceButton.isSelected = false

            fastPaceButton.config(PlanPace.FAST.getTitle(requireActivity()))
            fastPaceButton.setOnClickListener {
                viewModel.setPlanPace(PlanPace.FAST)
            }

            balancedPaceButton.config(PlanPace.BALANCED.getTitle(requireActivity()), true)
            balancedPaceButton.setOnClickListener {
                viewModel.setPlanPace(PlanPace.BALANCED)
            }

            slowPaceButton.config(PlanPace.SLOW.getTitle(requireActivity()))
            slowPaceButton.setOnClickListener {
                viewModel.setPlanPace(PlanPace.SLOW)
            }
        }
    }

    private fun setupBinding() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.planPace.collect { planPace ->
                viewBinding.apply {
                    fastPaceButton.isSelected = planPace == PlanPace.FAST
                    balancedPaceButton.isSelected = planPace == PlanPace.BALANCED
                    slowPaceButton.isSelected = planPace == PlanPace.SLOW
                }
                planPace?.let {
                    delegate?.didSelectPlanPace(it)
                }
            }
        }
    }

    fun resetData() {
        viewModel.setPlanPace(null)
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