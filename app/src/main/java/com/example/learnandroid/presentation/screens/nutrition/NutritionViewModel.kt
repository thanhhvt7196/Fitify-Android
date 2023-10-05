package com.example.learnandroid.presentation.screens.nutrition

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.learnandroid.presentation.screens.base.BaseViewModel

class NutritionViewModel : BaseViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is notifications Fragment"
    }
    val text: LiveData<String> = _text
}