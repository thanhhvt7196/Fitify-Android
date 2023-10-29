package com.example.learnandroid.presentation.screens.onboarding.source

import androidx.lifecycle.viewModelScope
import com.example.learnandroid.domain.models.Source
import com.example.learnandroid.presentation.screens.base.BaseViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

class OnboardingSourceViewModel: BaseViewModel() {
    private val _allSources = MutableStateFlow(Source.values().map { Pair(it, false) })
    val allSources = _allSources.asStateFlow()

    private val selectedSource = MutableSharedFlow<Source>()

    init {
        setupBinding()
    }

    private fun setupBinding() {
        viewModelScope.launch {
            selectedSource
                .map { selectedSource ->
                    return@map _allSources.value.map { (source, _) ->
                        if (source == selectedSource) {
                            source to true
                        } else {
                            source to false
                        }
                    }
                }
                .collect {
                    _allSources.value = it
                }
        }
    }

    fun selectSource(source: Source) {
        viewModelScope.launch {
            selectedSource.emit(source)
        }
    }

    fun resetData() {
        _allSources.value = Source.values().map { Pair(it, false) }
    }
}