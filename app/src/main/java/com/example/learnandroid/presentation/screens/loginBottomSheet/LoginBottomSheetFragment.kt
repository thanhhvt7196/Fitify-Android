package com.example.learnandroid.presentation.screens.loginBottomSheet

import androidx.fragment.app.viewModels
import com.example.learnandroid.databinding.FragmentLoginBottomSheetBinding
import com.example.learnandroid.domain.models.SocialType
import com.example.learnandroid.presentation.screens.base.BaseBottomSheetViewBindingFragment

interface LoginBottomSheetDelegate {
    fun didSelectLoginType(type: LoginType)
}

class LoginBottomSheetFragment :
    BaseBottomSheetViewBindingFragment<FragmentLoginBottomSheetBinding, LoginBottomSheetViewModel>(
        FragmentLoginBottomSheetBinding::inflate
    ) {
    override val viewModel: LoginBottomSheetViewModel by viewModels()

    private var delegate: LoginBottomSheetDelegate? = null

    override fun setup() {
        super.setup()
        setupUI()
    }

    private fun setupUI() {
        viewBinding.apply {
            appleButton.config(SocialType.APPLE)
            appleButton.setOnClickListener {
                dismiss()
                delegate?.didSelectLoginType(LoginType.LoginSocial(SocialType.APPLE))
            }

            googleButton.config(SocialType.GOOGLE)
            googleButton.setOnClickListener {
                dismiss()
                delegate?.didSelectLoginType(LoginType.LoginSocial(SocialType.GOOGLE))
            }

            facebookButton.config(SocialType.FACEBOOK)
            facebookButton.setOnClickListener {
                dismiss()
                delegate?.didSelectLoginType(LoginType.LoginSocial(SocialType.FACEBOOK))
            }

            emailButton.setOnClickListener {
                delegate?.didSelectLoginType(LoginType.LoginEmail)
                dismiss()
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        delegate = null
    }

    override fun onDestroy() {
        super.onDestroy()
        delegate = null
    }

    fun setAction(delegate: LoginBottomSheetDelegate?) {
        this.delegate = delegate
    }

    companion object {
        fun newInstance(): LoginBottomSheetFragment {
            return LoginBottomSheetFragment()
        }
    }
}