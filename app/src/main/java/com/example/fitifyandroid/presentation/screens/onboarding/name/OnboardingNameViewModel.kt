package com.example.fitifyandroid.presentation.screens.onboarding.name

import com.example.fitifyandroid.presentation.screens.base.BaseViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class OnboardingNameViewModel : BaseViewModel() {
    private val _name = MutableStateFlow("")
    val name = _name.asStateFlow()

    fun setName(value: String) {
        _name.value = value
    }
}