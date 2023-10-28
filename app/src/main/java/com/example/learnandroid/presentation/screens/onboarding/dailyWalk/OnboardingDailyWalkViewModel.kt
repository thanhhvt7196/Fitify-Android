package com.example.learnandroid.presentation.screens.onboarding.dailyWalk

import androidx.lifecycle.viewModelScope
import com.example.learnandroid.domain.models.DailyWalk
import com.example.learnandroid.presentation.screens.base.BaseViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch

class OnboardingDailyWalkViewModel: BaseViewModel() {
    private val _dailyWalk = MutableSharedFlow<DailyWalk?>()
    val dailyWalk = _dailyWalk.asSharedFlow()

    fun setDailyWalk(dailyWalk: DailyWalk?) {
        viewModelScope.launch {
            _dailyWalk.emit(dailyWalk)
        }
    }
}