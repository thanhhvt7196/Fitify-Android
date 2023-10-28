package com.example.learnandroid.presentation.screens.login

import androidx.lifecycle.viewModelScope
import com.example.learnandroid.domain.models.ActiveStatus
import com.example.learnandroid.domain.models.FitnessTool
import com.example.learnandroid.domain.models.Gender
import com.example.learnandroid.domain.models.KneePain
import com.example.learnandroid.domain.models.OnboardingGoal
import com.example.learnandroid.domain.models.PushUp
import com.example.learnandroid.domain.models.WorkoutFrequency
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
    private val _age = MutableSharedFlow<Int?>()
    private val _height = MutableSharedFlow<Int?>()
    private val _weight = MutableSharedFlow<Float?>()
    private val _targetWeight = MutableSharedFlow<Float?>()
    private val _kneePain = MutableSharedFlow<KneePain?>()
    private val _fitnessTools = MutableSharedFlow<List<FitnessTool>>()
    private val _activeStatus = MutableSharedFlow<ActiveStatus?>()
    private val _frequency = MutableSharedFlow<WorkoutFrequency?>()
    private val _pushUp = MutableSharedFlow<PushUp?>()

    val gender: SharedFlow<Gender?> = _gender.asSharedFlow()
    val name: SharedFlow<String?> = _name.asSharedFlow()
    val goal: SharedFlow<OnboardingGoal?> = _goal.asSharedFlow()
    val age = _age.asSharedFlow()
    val height = _height.asSharedFlow()
    val weight = _weight.asSharedFlow()
    val targetWeight = _targetWeight.asSharedFlow()
    val kneePain = _kneePain.asSharedFlow()
    val fitnessTools = _fitnessTools.asSharedFlow()
    val activeStatus = _activeStatus.asSharedFlow()
    val frequency = _frequency.asSharedFlow()
    val pushUp = _pushUp.asSharedFlow()

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

    fun setAge(age: Int?) {
        viewModelScope.launch {
            _age.emit(age)
        }
    }

    fun setHeight(height: Int?) {
        viewModelScope.launch {
            _height.emit(height)
        }
    }

    fun setWeight(weight: Float?) {
        viewModelScope.launch {
            _weight.emit(weight)
        }
    }

    fun setTargetWeight(weight: Float?) {
        viewModelScope.launch {
            _targetWeight.emit(weight)
        }
    }

    fun setKneePain(kneePain: KneePain?) {
        viewModelScope.launch {
            _kneePain.emit(kneePain)
        }
    }

    fun setFitnessTools(tools: List<FitnessTool>) {
        viewModelScope.launch {
            _fitnessTools.emit(tools)
        }
    }

    fun setActiveStatus(status: ActiveStatus?) {
        viewModelScope.launch {
            _activeStatus.emit(status)
        }
    }

    fun setFrequency(frequency: WorkoutFrequency?) {
        viewModelScope.launch {
            _frequency.emit(frequency)
        }
    }

    fun setPushUp(pushUp: PushUp?) {
        viewModelScope.launch {
            _pushUp.emit(pushUp)
        }
    }
}