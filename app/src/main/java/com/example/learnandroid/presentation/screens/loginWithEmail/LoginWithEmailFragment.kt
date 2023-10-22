package com.example.learnandroid.presentation.screens.loginWithEmail

import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.learnandroid.R
import com.example.learnandroid.databinding.FragmentLoginWithEmailBinding
import com.example.learnandroid.presentation.screens.base.BaseViewBindingFragment

class LoginWithEmailFragment : BaseViewBindingFragment<FragmentLoginWithEmailBinding, LoginWithEmailViewModel>(FragmentLoginWithEmailBinding::inflate) {
    override val viewModel: LoginWithEmailViewModel by viewModels()

    override fun initView() {
        viewBinding.apply {
            testButton.setOnClickListener {
                findNavController().navigate(R.id.action_loginWithEmailFragment_to_login_fragment)
            }
        }
    }

    override suspend fun subscribeData() {

    }

    companion object {
        const val tag = "LoginWithEmailFragment"
        fun newInstance(): LoginWithEmailFragment {
            return LoginWithEmailFragment()
        }
    }
}