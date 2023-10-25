package com.example.learnandroid.presentation.screens.onboarding.name

import com.example.learnandroid.presentation.screens.base.BaseViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class OnboardingNameViewModel: BaseViewModel() {
    private val _name = MutableStateFlow<String>("")
    val name = _name.asStateFlow()

    fun setName(value: String) {
        _name.value = value
    }
}