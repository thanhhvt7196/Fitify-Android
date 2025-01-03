package com.example.fitifyandroid.presentation.screens.forgotPassword

import android.content.Context
import android.text.InputType
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.fitifyandroid.R
import com.example.fitifyandroid.databinding.FragmentForgotPasswordBinding
import com.example.fitifyandroid.presentation.components.alert.AlertHelper
import com.example.fitifyandroid.presentation.components.appToolBar.AppToolbar
import com.example.fitifyandroid.presentation.screens.base.BaseViewBindingFragment
import com.example.fitifyandroid.utils.constants.AppConstants
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

    override fun onAttach(context: Context) {
        super.onAttach(context)
        viewModel.emailInvalidMessage = AppConstants.emailInvalidMessage(context)
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
            emailTextField.setMaxLength(AppConstants.EMAIL_MAXIMUM_CHARACTERS)
            emailTextField.setTextChangeHandler { text ->
                viewModel.setEmail(text)
            }
            emailTextField.focus(requireActivity())

            resetButton.setOnClickListener {
                viewModel.requestResetPassword()
                emailTextField.unfocus(requireActivity())
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
        fun newInstance(): ForgotPasswordFragment {
            return ForgotPasswordFragment()
        }
    }
}