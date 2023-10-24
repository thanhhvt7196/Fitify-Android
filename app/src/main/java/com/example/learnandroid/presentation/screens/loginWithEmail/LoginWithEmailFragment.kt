package com.example.learnandroid.presentation.screens.loginWithEmail

import android.text.InputType
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.learnandroid.R
import com.example.learnandroid.databinding.FragmentLoginWithEmailBinding
import com.example.learnandroid.presentation.components.appToolBar.AppToolbar
import com.example.learnandroid.presentation.screens.base.BaseViewBindingFragment
import com.example.learnandroid.utils.constants.AppConstants
import kotlinx.coroutines.launch

class LoginWithEmailFragment : BaseViewBindingFragment<FragmentLoginWithEmailBinding, LoginWithEmailViewModel>(FragmentLoginWithEmailBinding::inflate) {
    override val viewModel: LoginWithEmailViewModel by viewModels()

    override fun setup() {
        super.setup()
        viewBinding.apply {
            toolbar.setBackButtonType(AppToolbar.BackButtonType.DISMISS)
            toolbar.setBackButtonOnClickListener {
                findNavController().popBackStack()
            }
            context?.let {
                toolbar.setTitle(it.getString(R.string.fitify_logo))
            }

            emailTextField.setKeyboardType(InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS)
            emailTextField.setPlaceholder(context?.getString(R.string.hint_email) ?: "")
            emailTextField.setMaxLength(AppConstants.emailMaximumCharacters)
            emailTextField.setTextChangeHandler { text ->
                viewModel.setEmail(text)
            }

            passwordTextField.setKeyboardType(InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PASSWORD)
            passwordTextField.setPlaceholder(context?.getString(R.string.hint_password) ?: "")
            passwordTextField.setMaxLength(AppConstants.passwordMaximumCharacters)
            passwordTextField.setTextChangeHandler { text ->
                viewModel.setPassword(text)
            }

            loginButton.setOnClickListener {
                viewModel.requestLogin()
            }

            forgotPasswordButton.setOnClickListener {
                findNavController().navigate(R.id.login_email_to_forgot_password)
            }

            viewModel.setEmail(emailTextField.getText())
            viewModel.setPassword(passwordTextField.getText())
        }

        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.isValidData.collect { isValid ->
                viewBinding.loginButton.isEnabled = isValid
            }
        }
    }

    companion object {
        const val tag = "LoginWithEmailFragment"
        fun newInstance(): LoginWithEmailFragment {
            return LoginWithEmailFragment()
        }
    }
}