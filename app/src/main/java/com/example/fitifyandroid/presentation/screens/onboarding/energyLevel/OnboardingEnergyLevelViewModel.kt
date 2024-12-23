package com.example.fitifyandroid.presentation.screens.onboarding.energyLevel

import androidx.lifecycle.viewModelScope
import com.example.fitifyandroid.domain.models.EnergyLevel
import com.example.fitifyandroid.presentation.screens.base.BaseViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch

class OnboardingEnergyLevelViewModel: BaseViewModel() {
    private val _energyLevel = MutableSharedFlow<EnergyLevel?>()
    val energyLevel = _energyLevel.asSharedFlow()

    fun setEnergyLevel(energyLevel: EnergyLevel?) {
        viewModelScope.launch {
            _energyLevel.emit(energyLevel)
        }
    }
}