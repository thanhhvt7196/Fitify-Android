package com.example.fitifyandroid.presentation.screens.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.fitifyandroid.utils.viewmodel.SingleLiveEvent

open class BaseViewModel : ViewModel() {
    protected val isLoadingData = MutableLiveData(false)
    val isLoading: LiveData<Boolean> = isLoadingData


    protected val errorMessageData = SingleLiveEvent<String>()
    val errorMessage: LiveData<String> get() = errorMessageData
}