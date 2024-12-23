package com.example.fitifyandroid.presentation.screens.loginBottomSheet

import com.example.fitifyandroid.domain.models.SocialType
import com.example.fitifyandroid.presentation.screens.base.BaseViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow

sealed class LoginType {
    object LoginEmail : LoginType()
    data class LoginSocial(val socialType: SocialType): LoginType()
}

class LoginBottomSheetViewModel: BaseViewModel() {
    private val _loginType = MutableSharedFlow<LoginType>()
    val loginType: SharedFlow<LoginType> = _loginType.asSharedFlow()
}