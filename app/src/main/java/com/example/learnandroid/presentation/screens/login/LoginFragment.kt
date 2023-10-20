package com.example.learnandroid.presentation.screens.login

import androidx.fragment.app.viewModels
import com.example.learnandroid.databinding.FragmentLoginBinding
import com.example.learnandroid.presentation.screens.base.BaseViewBindingFragment

class LoginFragment : BaseViewBindingFragment<FragmentLoginBinding, LoginViewModel>(FragmentLoginBinding::inflate) {

    override val viewModel: LoginViewModel by viewModels()

    override fun initView() {

    }

    override suspend fun subscribeData() {

    }
}