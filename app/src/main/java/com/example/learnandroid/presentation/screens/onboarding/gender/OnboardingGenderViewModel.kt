package com.example.learnandroid.presentation.screens.onboarding.gender

import com.example.learnandroid.domain.models.Gender
import com.example.learnandroid.presentation.screens.base.BaseViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow

class OnboardingGenderViewModel: BaseViewModel() {
    private val _gender = MutableSharedFlow<Gender?>()
    val gender: SharedFlow<Gender?> = _gender.asSharedFlow()

    suspend fun setGender(gender: Gender?) {
        _gender.emit(gender)
    }
}