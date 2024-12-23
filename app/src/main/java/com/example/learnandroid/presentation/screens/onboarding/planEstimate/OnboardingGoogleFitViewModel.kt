package com.example.learnandroid.presentation.screens.onboarding.planEstimate

import com.example.learnandroid.presentation.screens.base.BaseViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import java.time.LocalDate

class OnboardingGoogleFitViewModel: BaseViewModel() {
    private val _estimateDay = MutableStateFlow<LocalDate>(LocalDate.now().plusMonths(3))
    val estimateDay = _estimateDay.asStateFlow()

    fun resetData() {
        _estimateDay.value = LocalDate.now().plusMonths(3)
    }
}