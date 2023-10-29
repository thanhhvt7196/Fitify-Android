package com.example.learnandroid.presentation.screens.onboarding.planPace

import androidx.lifecycle.viewModelScope
import com.example.learnandroid.domain.models.PlanPace
import com.example.learnandroid.presentation.screens.base.BaseViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch

class OnboardingPlanPaceViewModel: BaseViewModel() {
    private val _planPace = MutableSharedFlow<PlanPace?>()
    val planPace = _planPace.asSharedFlow()

    fun setPlanPace(planPace: PlanPace?) {
        viewModelScope.launch {
            _planPace.emit(planPace)
        }
    }
}