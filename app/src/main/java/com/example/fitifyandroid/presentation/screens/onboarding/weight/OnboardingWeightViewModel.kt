package com.example.fitifyandroid.presentation.screens.onboarding.weight

import com.example.fitifyandroid.presentation.screens.base.BaseViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class OnboardingWeightViewModel: BaseViewModel() {
    private val _weight = MutableStateFlow<Float?>(null)
    val weight = _weight.asStateFlow()

    fun setWeight(value: Float?) {
        _weight.value = value
    }
}