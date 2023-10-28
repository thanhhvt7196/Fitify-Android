package com.example.learnandroid.presentation.screens.onboarding.workoutFrequency

import androidx.lifecycle.viewModelScope
import com.example.learnandroid.domain.models.WorkoutFrequency
import com.example.learnandroid.presentation.screens.base.BaseViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch

class OnboardingFrequencyViewModel: BaseViewModel() {
    private val _frequency = MutableSharedFlow<WorkoutFrequency?>()
    val frequency = _frequency.asSharedFlow()

    fun setFrequency(value: WorkoutFrequency?) {
        viewModelScope.launch {
            _frequency.emit(value)
        }
    }
}