package com.example.learnandroid.presentation.screens.onboarding.gender

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.learnandroid.domain.models.Gender
import com.example.learnandroid.presentation.screens.base.BaseViewModel

class OnboardingGenderViewModel: BaseViewModel() {
    private val _gender = MutableLiveData<Gender?>().apply {
        value = null
    }

    val gender: LiveData<Gender?> = _gender

    fun setGender(gender: Gender?) {
        _gender.postValue(gender)
    }
}