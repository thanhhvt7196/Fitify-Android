package com.example.learnandroid.presentation.screens.onboarding.fitnessTool

import androidx.lifecycle.viewModelScope
import com.example.learnandroid.domain.models.FitnessTool
import com.example.learnandroid.presentation.screens.base.BaseViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

class OnboardingFitnessToolViewModel : BaseViewModel() {
    private val _allTools = MutableStateFlow(FitnessTool.values().map { Pair(it, false) })
    val allTools = _allTools.asStateFlow()

    private val selectedTool = MutableSharedFlow<FitnessTool>()

    init {
        setupBinding()
    }

    private fun setupBinding() {
        viewModelScope.launch {
            selectedTool
                .map { tool ->
                    return@map _allTools.value.map { (fitnessTool, isSelected) ->
                        if (fitnessTool == tool) {
                            fitnessTool to !isSelected
                        } else {
                            fitnessTool to isSelected
                        }
                    }
                }
                .collect {
                    _allTools.value = it
                }
        }
    }

    fun selectTool(tool: FitnessTool) {
        viewModelScope.launch {
            selectedTool.emit(tool)
        }
    }

    fun resetData() {
        _allTools.value = FitnessTool.values().map { Pair(it, false) }
    }
}