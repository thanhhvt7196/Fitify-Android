package com.example.fitifyandroid.presentation.screens.loginWithEmail

import androidx.lifecycle.viewModelScope
import com.example.fitifyandroid.presentation.screens.base.BaseViewModel
import com.example.fitifyandroid.utils.constants.AppConstants
import com.example.fitifyandroid.utils.extensions.isEmail
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.launch

class LoginWithEmailViewModel : BaseViewModel() {
    private val _email = MutableStateFlow("")
    private val _password = MutableStateFlow("")

    private val _isValidData = MutableStateFlow(false)
    val isValidData: StateFlow<Boolean> = _isValidData.asStateFlow()

    var emailInvalidMessage = ""
    var passwordShortMessage = ""

    init {
        bindingData()
    }

    private fun bindingData() {
        viewModelScope.launch {
            _email
                .combine(_password) { newEmail, newPassword ->
                    Pair(newEmail, newPassword)
                }
                .collect { (newEmail, newPassword) ->
                    validateData(newEmail, newPassword)?.let {
                        errorMessageData.postValue(it)
                        _isValidData.value = false
                    } ?: run {
                        _isValidData.value = true
                    }
                }
        }
    }

    private fun validateData(email: String, password: String): String? {
        if (!email.isEmail()) {
            return emailInvalidMessage
        }
        if (password.length < AppConstants.PASSWORD_MINIMUM_CHARACTERS || password.length > AppConstants.PASSWORD_MAXIMUM_CHARACTERS) {
            return passwordShortMessage
        }
        return null
    }

    fun requestLogin() {
        login(_email.value, _password.value)
    }

    private fun login(email: String, password: String) {
        isLoadingData.value = true
        validateData(email, password)?.let { message ->
            errorMessageData.postValue(message)
            isLoadingData.value = false
        } ?: run {
            viewModelScope.launch {
                delay(3000)
                isLoadingData.value = false
            }
        }

    }

    fun setEmail(value: String) {
        _email.value = value
    }

    fun setPassword(value: String) {
        _password.value = value
    }
}