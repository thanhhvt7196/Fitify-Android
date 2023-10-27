package com.example.learnandroid.presentation.screens.login

import androidx.lifecycle.viewModelScope
import com.example.learnandroid.domain.models.Gender
import com.example.learnandroid.domain.models.OnboardingGoal
import com.example.learnandroid.presentation.screens.base.BaseViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class LoginViewModel: BaseViewModel() {
    private val _currentIndex = MutableStateFlow<Int>(0)
    val currentIndex: StateFlow<Int> = _currentIndex.asStateFlow()

    private val _gender = MutableSharedFlow<Gender?>()
    private val _name = MutableSharedFlow<String?>()
    private val _goal = MutableSharedFlow<OnboardingGoal?>()
    private val _age = MutableSharedFlow<Int>()
    private val _height = MutableSharedFlow<Int>()
    private val _weight = MutableSharedFlow<Int>()


    val gender: SharedFlow<Gender?> = _gender.asSharedFlow()
    val name: SharedFlow<String?> = _name.asSharedFlow()
    val goal: SharedFlow<OnboardingGoal?> = _goal.asSharedFlow()
    val age = _age.asSharedFlow()
    val height = _height.asSharedFlow()
    val weight = _weight.asSharedFlow()

    fun setIndex(index: Int) {
        _currentIndex.value = index
    }

    fun setGender(gender: Gender) {
        viewModelScope.launch {
            _gender.emit(gender)
        }
    }

    fun setName(name: String) {
        viewModelScope.launch {
            _name.emit(name)
        }
    }

    fun setGoal(goal: OnboardingGoal) {
        viewModelScope.launch {
            _goal.emit(goal)
        }
    }

    fun setAge(age: Int) {
        viewModelScope.launch {
            _age.emit(age)
        }
    }

    fun setHeight(height: Int) {
        viewModelScope.launch {
            _height.emit(height)
        }
    }

    fun setWeight(weight: Int) {
        viewModelScope.launch {
            _weight.emit(weight)
        }
    }
}