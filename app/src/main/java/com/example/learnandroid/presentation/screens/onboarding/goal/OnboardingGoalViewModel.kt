package com.example.learnandroid.presentation.screens.onboarding.goal

import androidx.lifecycle.viewModelScope
import com.example.learnandroid.domain.models.OnboardingGoal
import com.example.learnandroid.presentation.screens.base.BaseViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch

class OnboardingGoalViewModel : BaseViewModel() {
    private val _goal = MutableSharedFlow<OnboardingGoal?>()
    val goal = _goal.asSharedFlow()

    fun setGoal(value: OnboardingGoal?) {
        viewModelScope.launch {
            _goal.emit(value)
        }
    }
}