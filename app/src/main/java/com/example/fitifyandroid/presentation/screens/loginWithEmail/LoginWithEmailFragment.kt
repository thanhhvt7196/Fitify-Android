package com.example.fitifyandroid.presentation.screens.loginWithEmail

import android.content.Context
import android.text.InputType
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.fitifyandroid.R
import com.example.fitifyandroid.databinding.FragmentLoginWithEmailBinding
import com.example.fitifyandroid.presentation.components.appToolBar.AppToolbar
import com.example.fitifyandroid.presentation.screens.base.BaseViewBindingFragment
import com.example.fitifyandroid.utils.constants.AppConstants
import kotlinx.coroutines.launch

class LoginWithEmailFragment :
    BaseViewBindingFragment<FragmentLoginWithEmailBinding, LoginWithEmailViewModel>(
        FragmentLoginWithEmailBinding::inflate
    ) {
    override val viewModel: LoginWithEmailViewModel by viewModels()

    override fun onAttach(context: Context) {
        super.onAttach(context)
        viewModel.emailInvalidMessage = AppConstants.emailInvalidMessage(context)
        viewModel.passwordShortMessage = AppConstants.passwordShortMessage(context)
    }

    override fun setup() {
        super.setup()
        setupUI()
        setupBinding()
    }

    private fun setupUI() {
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
            emailTextField.setMaxLength(AppConstants.EMAIL_MAXIMUM_CHARACTERS)
            emailTextField.setTextChangeHandler { text ->
                viewModel.setEmail(text)
            }
            emailTextField.focus(requireActivity())

            passwordTextField.setKeyboardType(InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PASSWORD)
            passwordTextField.setPlaceholder(context?.getString(R.string.hint_password) ?: "")
            passwordTextField.setMaxLength(AppConstants.PASSWORD_MAXIMUM_CHARACTERS)
            passwordTextField.setTextChangeHandler { text ->
                viewModel.setPassword(text)
            }

            loginButton.setOnClickListener {
                viewModel.requestLogin()
                emailTextField.unfocus(requireActivity())
                passwordTextField.unfocus(requireActivity())
            }

            forgotPasswordButton.setOnClickListener {
                findNavController().navigate(R.id.login_email_to_forgot_password)
            }

            viewModel.setEmail(emailTextField.getText())
            viewModel.setPassword(passwordTextField.getText())
        }
    }

    private fun setupBinding() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.isValidData.collect { isValid ->
                viewBinding.loginButton.isEnabled = isValid
            }
        }
    }

    override fun onPause() {
        super.onPause()
        viewBinding.emailTextField.unfocus(requireActivity())
    }

    companion object {
        fun newInstance(): LoginWithEmailFragment {
            return LoginWithEmailFragment()
        }
    }
}