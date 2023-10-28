package com.example.learnandroid.presentation.screens.onboarding.height

import com.example.learnandroid.presentation.screens.base.BaseViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class OnboardingHeightViewModel: BaseViewModel() {
    private val _height = MutableStateFlow<Int?>(0)
    val height = _height.asStateFlow()

    fun setHeight(value: Int?) {
        _height.value = value
    }
}