package com.example.fitifyandroid.presentation.screens.onboarding.height

import com.example.fitifyandroid.presentation.screens.base.BaseViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class OnboardingHeightViewModel: BaseViewModel() {
    private val _height = MutableStateFlow<Int?>(0)
    val height = _height.asStateFlow()

    fun setHeight(value: Int?) {
        _height.value = value
    }
}