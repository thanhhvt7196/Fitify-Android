package com.example.learnandroid.presentation.screens.forgotPassword

import androidx.lifecycle.viewModelScope
import com.example.learnandroid.presentation.screens.base.BaseViewModel
import com.example.learnandroid.utils.extensions.isEmail
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class ForgotPasswordViewModel : BaseViewModel() {
    private val _email = MutableStateFlow("")

    private val _isValidData = MutableStateFlow(false)
    val isValidData: StateFlow<Boolean> = _isValidData.asStateFlow()

    private val _requestSuccess = MutableSharedFlow<Unit>()
    val requestSuccess: SharedFlow<Unit> = _requestSuccess.asSharedFlow()
    var emailInvalidMessage = ""

    init {
        bindingData()
    }

    private fun bindingData() {
        viewModelScope.launch {
            _email
                .collect { newEmail ->
                    validateData(newEmail)?.let {
                        errorMessageData.postValue(it)
                        _isValidData.value = false
                    } ?: run {
                        _isValidData.value = true
                    }
                }
        }
    }

    private fun validateData(email: String): String? {
        return if (email.isEmail()) null else emailInvalidMessage
    }

    fun requestResetPassword() {
        resetPassword(_email.value)
    }

    private fun resetPassword(email: String) {
        isLoadingData.value = true
        validateData(email)?.let { message ->
            errorMessageData.postValue(message)
            isLoadingData.value = false
        } ?: run {
            viewModelScope.launch {
                delay(3000)
                isLoadingData.value = false
                _requestSuccess.emit(Unit)
            }
        }

    }

    fun setEmail(value: String) {
        _email.value = value
    }
}