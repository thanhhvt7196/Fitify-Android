package com.example.fitifyandroid.presentation.screens.onboarding.activeStatus

import androidx.lifecycle.viewModelScope
import com.example.fitifyandroid.domain.models.ActiveStatus
import com.example.fitifyandroid.presentation.screens.base.BaseViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch

class OnboardingActiveStatusViewModel: BaseViewModel() {
    private val _activeStatus = MutableSharedFlow<ActiveStatus?>()
    val activeStatus = _activeStatus.asSharedFlow()

    fun setActiveStatus(status: ActiveStatus?) {
        viewModelScope.launch {
            _activeStatus.emit(status)
        }
    }
}