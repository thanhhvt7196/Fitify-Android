package com.example.fitifyandroid.presentation.screens.onboarding.energyLevel

import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.fitifyandroid.databinding.FragmentOnboardingEnergyLevelBinding
import com.example.fitifyandroid.domain.models.EnergyLevel
import com.example.fitifyandroid.presentation.screens.base.BaseViewBindingFragment
import kotlinx.coroutines.launch

class OnboardingEnergyLevelFragment :
    BaseViewBindingFragment<FragmentOnboardingEnergyLevelBinding, OnboardingEnergyLevelViewModel>(
        FragmentOnboardingEnergyLevelBinding::inflate
    ) {
    override val viewModel: OnboardingEnergyLevelViewModel by viewModels()

    interface OnboardingEnergyLevelDelegate {
        fun didSelectEnergyLevel(energyLevel: EnergyLevel)
    }

    private var delegate: OnboardingEnergyLevelDelegate? = null

    companion object {
        fun newInstance(): OnboardingEnergyLevelFragment {
            return OnboardingEnergyLevelFragment()
        }
    }

    fun setAction(delegate: OnboardingEnergyLevelDelegate) {
        this.delegate = delegate
    }

    override fun setup() {
        super.setup()
        setupUI()
        setupBinding()
    }

    private fun setupUI() {
        viewBinding.apply {
            stableButton.isSelected = false
            tiredLunchTimeButton.isSelected = false
            needANapButton.isSelected = false

            stableButton.config(EnergyLevel.STABLE.getTitle(requireActivity()))
            stableButton.setOnClickListener {
                viewModel.setEnergyLevel(EnergyLevel.STABLE)
            }

            tiredLunchTimeButton.config(EnergyLevel.TIRED_LUNCH.getTitle(requireActivity()))
            tiredLunchTimeButton.setOnClickListener {
                viewModel.setEnergyLevel(EnergyLevel.TIRED_LUNCH)
            }

            needANapButton.config(EnergyLevel.SLEEP_AFTER_MEAL.getTitle(requireActivity()))
            needANapButton.setOnClickListener {
                viewModel.setEnergyLevel(EnergyLevel.SLEEP_AFTER_MEAL)
            }
        }
    }

    private fun setupBinding() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.energyLevel.collect { energyLevel ->
                viewBinding.apply {
                    stableButton.isSelected = energyLevel == EnergyLevel.STABLE
                    tiredLunchTimeButton.isSelected = energyLevel == EnergyLevel.TIRED_LUNCH
                    needANapButton.isSelected = energyLevel == EnergyLevel.SLEEP_AFTER_MEAL
                }
                energyLevel?.let {
                    delegate?.didSelectEnergyLevel(it)
                }
            }
        }
    }

    fun resetData() {
        viewModel.setEnergyLevel(null)
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