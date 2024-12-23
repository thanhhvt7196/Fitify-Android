package com.example.fitifyandroid.presentation.screens.onboarding.badHabit

import androidx.lifecycle.viewModelScope
import com.example.fitifyandroid.domain.models.BadHabit
import com.example.fitifyandroid.presentation.screens.base.BaseViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

class OnboardingBadHabitViewModel : BaseViewModel() {
    private val _allBadHabits = MutableStateFlow(BadHabit.values().map { Pair(it, false) })
    val allBadHabits = _allBadHabits.asStateFlow()

    private val selectedHabit = MutableSharedFlow<BadHabit>()

    init {
        setupBinding()
    }

    private fun setupBinding() {
        viewModelScope.launch {
            selectedHabit
                .map { habit ->
                    return@map _allBadHabits.value.map { (badHabit, isSelected) ->
                        if (badHabit == habit) {
                            badHabit to !isSelected
                        } else {
                            badHabit to isSelected
                        }
                    }
                }
                .collect {
                    _allBadHabits.value = it
                }
        }
    }

    fun selectHabit(habit: BadHabit) {
        viewModelScope.launch {
            selectedHabit.emit(habit)
        }
    }

    fun resetData() {
        _allBadHabits.value = BadHabit.values().map { Pair(it, false) }
    }
}