package com.example.fitifyandroid.presentation.screens.onboarding.pushup

import androidx.lifecycle.viewModelScope
import com.example.fitifyandroid.domain.models.PushUp
import com.example.fitifyandroid.presentation.screens.base.BaseViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch

class OnboardingPushUpViewModel: BaseViewModel() {
    private val _pushUp = MutableSharedFlow<PushUp?>()
    val pushUp = _pushUp.asSharedFlow()

    fun setPushUp(pushUp: PushUp?) {
        viewModelScope.launch {
            _pushUp.emit(pushUp)
        }
    }
}