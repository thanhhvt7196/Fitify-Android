package com.example.learnandroid.presentation.screens.loginWithEmail

import androidx.lifecycle.viewModelScope
import com.example.learnandroid.presentation.screens.base.BaseViewModel
import com.example.learnandroid.utils.constants.AppConstants
import com.example.learnandroid.utils.extensions.isEmail
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.launch

class LoginWithEmailViewModel : BaseViewModel() {


    private val _email = MutableStateFlow<String>("")
    val email: StateFlow<String> = _email.asStateFlow()
    private val _password = MutableStateFlow<String>("")
    val password: StateFlow<String> = _password.asStateFlow()

    private val _isValidData = MutableStateFlow<Boolean>(false)
    val isValidData: StateFlow<Boolean> = _isValidData.asStateFlow()

    private val _loginButtonTapped = MutableSharedFlow<Unit>()

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
                        _errorMessage.postValue(it)
                        _isValidData.value = false
                    } ?: run {
                        _isValidData.value = true
                    }
                }
        }
    }

    private fun validateData(email: String, password: String): String? {
        if (!email.isEmail()) {
            return AppConstants.emailInvalidMessage
        }
        if (password.length < AppConstants.passwordMinimumCharacters || password.length > AppConstants.passwordMaximumCharacters) {
            return AppConstants.passwordShortMessage
        }
        return null
    }

    fun requestLogin() {
        login(_email.value, _password.value)
    }

    private fun login(email: String, password: String) {
        _isLoading.value = true
        validateData(email, password)?.let { message ->
            _errorMessage.postValue(message)
            _isLoading.value = false
        } ?: run {
            viewModelScope.launch {
                delay(3000)
                _isLoading.value = false
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