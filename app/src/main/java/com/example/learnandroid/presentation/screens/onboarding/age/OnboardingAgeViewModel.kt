package com.example.learnandroid.presentation.screens.onboarding.age

import androidx.lifecycle.viewModelScope
import com.example.learnandroid.presentation.screens.base.BaseViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class OnboardingAgeViewModel: BaseViewModel() {
    private val _age = MutableStateFlow<Int?>(null)
    val age = _age.asStateFlow()

    fun setAge(value: Int?) {
        viewModelScope.launch {
            _age.value = value
        }
    }
}