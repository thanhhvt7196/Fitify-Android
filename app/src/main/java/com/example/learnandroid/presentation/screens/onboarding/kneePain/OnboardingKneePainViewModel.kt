package com.example.learnandroid.presentation.screens.onboarding.kneePain

import androidx.lifecycle.viewModelScope
import com.example.learnandroid.domain.models.KneePain
import com.example.learnandroid.presentation.screens.base.BaseViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch

class OnboardingKneePainViewModel : BaseViewModel() {
    private val _kneePain = MutableSharedFlow<KneePain?>()
    val kneePain = _kneePain.asSharedFlow()

    fun setKneePain(value: KneePain?) {
        viewModelScope.launch {
            _kneePain.emit(value)
        }
    }
}