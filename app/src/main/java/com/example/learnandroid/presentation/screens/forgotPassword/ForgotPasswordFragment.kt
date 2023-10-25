package com.example.learnandroid.presentation.screens.forgotPassword

import android.text.InputType
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.learnandroid.R
import com.example.learnandroid.databinding.FragmentForgotPasswordBinding
import com.example.learnandroid.presentation.components.alert.AlertHelper
import com.example.learnandroid.presentation.components.appToolBar.AppToolbar
import com.example.learnandroid.presentation.screens.base.BaseViewBindingFragment
import com.example.learnandroid.utils.constants.AppConstants
import kotlinx.coroutines.launch

class ForgotPasswordFragment :
    BaseViewBindingFragment<FragmentForgotPasswordBinding, ForgotPasswordViewModel>(
        FragmentForgotPasswordBinding::inflate
    ) {
    override val viewModel: ForgotPasswordViewModel by viewModels()

    override fun setup() {
        super.setup()
        setupUI()
        setupBinding()
    }

    private fun setupUI() {
        viewBinding.apply {
            toolbar.setBackButtonType(AppToolbar.BackButtonType.POP)
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
            emailTextField.focus(requireActivity())

            resetButton.setOnClickListener {
                viewModel.requestResetPassword()
            }

            viewModel.setEmail(emailTextField.getText())
        }
    }

    private fun setupBinding() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.isValidData.collect { isValid ->
                viewBinding.resetButton.isEnabled = isValid
            }
        }

        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.requestSuccess.collect {
                AlertHelper.showAlert(
                    requireActivity(),
                    requireActivity().getString(R.string.forgot_password_continue),
                    requireActivity().getString(R.string.forgot_password_continue_email)
                )
                findNavController().popBackStack()
            }
        }
    }

    override fun onPause() {
        super.onPause()
        viewBinding.emailTextField.unfocus(requireActivity())
    }

    companion object {
        const val tag = "ForgotPasswordFragment"
        fun newInstance(): ForgotPasswordFragment {
            return ForgotPasswordFragment()
        }
    }
}