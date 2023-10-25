package com.example.learnandroid.presentation.screens.onboarding.goal

import com.example.learnandroid.domain.models.Gender
import com.example.learnandroid.domain.models.OnboardingGoal
import com.example.learnandroid.presentation.screens.base.BaseViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class OnboardingGoalViewModel: BaseViewModel() {
    private val _goal = MutableStateFlow<OnboardingGoal?>(null)
    val goal = _goal.asStateFlow()

    fun setGoal(value: OnboardingGoal?) {
        _goal.value = value
    }
}