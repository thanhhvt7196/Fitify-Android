package com.example.fitifyandroid.presentation.screens.onboarding.planDay

import androidx.lifecycle.viewModelScope
import com.example.fitifyandroid.domain.models.WeekDay
import com.example.fitifyandroid.presentation.screens.base.BaseViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

class OnboardingPlanDayViewModel : BaseViewModel() {
    private val _planDays = MutableStateFlow<List<WeekDay>>(emptyList())
    val planDays = _planDays.asStateFlow()
    private val _notificationOn = MutableStateFlow(false)
    var notificationOn = _notificationOn.asStateFlow()
    private val _selectedDay = MutableSharedFlow<WeekDay>()

    init {
        setupBinding()
    }

    private fun setupBinding() {
        viewModelScope.launch {
            _selectedDay
                .map { day ->
                    val newPlanDays = _planDays.value.toMutableList()
                    if (newPlanDays.contains(day)) {
                        newPlanDays.remove(day)
                    } else {
                        newPlanDays.add(day)
                    }
                    return@map newPlanDays
                }
                .collect {
                    _planDays.value = it
                }
        }
    }

    fun selectDay(day: WeekDay) {
        viewModelScope.launch {
            _selectedDay.emit(day)
        }
    }

    fun setNotificationOn(isSelected: Boolean) {
        viewModelScope.launch {
            _notificationOn.value = isSelected
        }
    }

    fun resetData() {
        _planDays.value = emptyList()
    }
}