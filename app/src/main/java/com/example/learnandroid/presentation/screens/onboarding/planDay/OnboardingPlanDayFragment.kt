package com.example.learnandroid.presentation.screens.onboarding.planDay

import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.learnandroid.R
import com.example.learnandroid.databinding.FragmentOnboardingPlanDayBinding
import com.example.learnandroid.domain.models.WeekDay
import com.example.learnandroid.presentation.screens.base.BaseViewBindingFragment
import kotlinx.coroutines.launch

class OnboardingPlanDayFragment :
    BaseViewBindingFragment<FragmentOnboardingPlanDayBinding, OnboardingPlanDayViewModel>(
        FragmentOnboardingPlanDayBinding::inflate
    ) {
    override val viewModel: OnboardingPlanDayViewModel by viewModels()

    interface OnboardingPlanDayDelegate {
        fun didSelectPlanDays(planDays: List<WeekDay>)
    }

    private var delegate: OnboardingPlanDayDelegate? = null

    companion object {
        fun newInstance(): OnboardingPlanDayFragment {
            return OnboardingPlanDayFragment()
        }
    }

    fun setAction(delegate: OnboardingPlanDayDelegate) {
        this.delegate = delegate
    }

    override fun setup() {
        super.setup()
        setupUI()
        setupBinding()
    }

    private fun setupUI() {
        viewBinding.apply {
            messageTextView.text = requireActivity().resources.getQuantityString(
                R.plurals.onboarding_scheduler_subtitle_pick_default,
                3,
                3
            )
            arrayOf(
                mondayButton,
                tuesdayButton,
                wednesdayButton,
                thursdayButton,
                fridayButton,
                saturdayButton,
                sundayButton
            ).forEachIndexed { index, button ->
                button.isSelected = false
                button.isToday = false
                button.setOnClickListener {
                    if (index < WeekDay.entries.count()) {
                        val weekDay = WeekDay.entries[index]
                        viewModel.selectDay(weekDay)
                    }
                }
            }

            continueButton.setOnClickListener {
                delegate?.didSelectPlanDays(viewModel.planDays.value)
            }

            switchControl.showText = false
            switchControl.setThumbResource(R.drawable.switch_thumb);
            switchControl.setTrackResource(R.drawable.switch_track);
            switchControl.setOnCheckedChangeListener { _, isChecked ->
                viewModel.setNotificationOn(isChecked)
            }
        }
    }

    private fun setupBinding() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.notificationOn
                .collect { isNotificationOn ->
                    viewBinding.switchControl.setChecked(isNotificationOn)
                }
        }

        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.planDays
                .collect { planDays ->
                    viewBinding.mondayButton.isSelected = planDays.contains(WeekDay.MONDAY)
                    viewBinding.mondayButton.isToday = WeekDay.MONDAY.isToday()
                    viewBinding.tuesdayButton.isSelected = planDays.contains(WeekDay.TUESDAY)
                    viewBinding.tuesdayButton.isToday = WeekDay.TUESDAY.isToday()
                    viewBinding.wednesdayButton.isSelected = planDays.contains(WeekDay.WEDNESDAY)
                    viewBinding.wednesdayButton.isToday = WeekDay.WEDNESDAY.isToday()
                    viewBinding.thursdayButton.isSelected = planDays.contains(WeekDay.THURSDAY)
                    viewBinding.thursdayButton.isToday = WeekDay.THURSDAY.isToday()
                    viewBinding.fridayButton.isSelected = planDays.contains(WeekDay.FRIDAY)
                    viewBinding.fridayButton.isToday = WeekDay.FRIDAY.isToday()
                    viewBinding.saturdayButton.isSelected = planDays.contains(WeekDay.SATURDAY)
                    viewBinding.saturdayButton.isToday = WeekDay.SATURDAY.isToday()
                    viewBinding.sundayButton.isSelected = planDays.contains(WeekDay.SUNDAY)
                    viewBinding.sundayButton.isToday = WeekDay.SUNDAY.isToday()

                    val isEnabled = planDays.isNotEmpty()
                    viewBinding.continueButton.isEnabled = isEnabled
                    viewBinding.continueButton.alpha = if (isEnabled) 1f else 0.3f
                }
        }
    }

    fun resetData() {
        viewModel.resetData()
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