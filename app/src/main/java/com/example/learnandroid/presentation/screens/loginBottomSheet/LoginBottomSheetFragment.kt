package com.example.learnandroid.presentation.screens.loginBottomSheet

import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.viewModelScope
import com.example.learnandroid.databinding.FragmentLoginBottomSheetBinding
import com.example.learnandroid.domain.models.SocialType
import com.example.learnandroid.presentation.screens.base.BaseBottomSheetViewBindingFragment
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import java.lang.ref.WeakReference

class LoginBottomSheetFragment : BaseBottomSheetViewBindingFragment<FragmentLoginBottomSheetBinding, LoginBottomSheetViewModel>(FragmentLoginBottomSheetBinding::inflate) {
    override val viewModel: LoginBottomSheetViewModel by viewModels()

    private val _loginType = MutableSharedFlow<LoginType>()
    val loginType: SharedFlow<LoginType> = _loginType.asSharedFlow()

    override fun initView() {
        viewBinding.apply {
            appleButton.config(SocialType.APPLE)
            appleButton.setOnClickListener {
                dismiss()
                lifecycleScope.launch {
                    _loginType.emit(LoginType.LoginSocial(SocialType.APPLE))
                }
            }

            googleButton.config(SocialType.GOOGLE)
            googleButton.setOnClickListener {
                dismiss()
                lifecycleScope.launch {
                    _loginType.emit(LoginType.LoginSocial(SocialType.GOOGLE))
                }
            }

            facebookButton.config(SocialType.FACEBOOK)
            facebookButton.setOnClickListener {
                dismiss()
                lifecycleScope.launch {
                    _loginType.emit(LoginType.LoginSocial(SocialType.FACEBOOK))
                }
            }

            emailButton.setOnClickListener {
                dismiss()
                lifecycleScope.launch {
                    _loginType.emit(LoginType.LoginEmail)
                }
            }
        }
    }

    override suspend fun subscribeData() {

    }

    companion object {
        const val tag = "LoginBottomSheet"
        fun newInstance(): LoginBottomSheetFragment {
            return LoginBottomSheetFragment()
        }
    }
}