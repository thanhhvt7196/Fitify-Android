package com.example.learnandroid.ui.screens.splash

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.learnandroid.ui.screens.base.BaseViewModel

class SplashViewModel() : BaseViewModel() {
    private val _text = MutableLiveData<String>().apply {
        value = "This is dashboard Fragment"
    }
    val text: LiveData<String> = _text

    fun setText(newValue: String) {
        _text.postValue(newValue)
    }
}