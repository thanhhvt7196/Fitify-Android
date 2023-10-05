package com.example.learnandroid.presentation.screens.plan

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.learnandroid.presentation.screens.base.BaseViewModel

class PlansViewModel : BaseViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is home Fragment"
    }
    val text: LiveData<String> = _text

    var type = 0
}