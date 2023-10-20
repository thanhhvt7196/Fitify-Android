package com.example.learnandroid.presentation.screens.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.learnandroid.presentation.screens.base.BaseViewModel

class LoginViewModel: BaseViewModel() {
    private val _currentIndex = MutableLiveData<Int>().apply {
        value = 0
    }
    val currentIndex: LiveData<Int> = _currentIndex

    fun setIndex(index: Int) {
        _currentIndex.postValue(index)
    }
}