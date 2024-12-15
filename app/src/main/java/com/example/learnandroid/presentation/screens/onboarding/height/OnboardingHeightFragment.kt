package com.example.learnandroid.presentation.screens.onboarding.height

import android.view.inputmethod.EditorInfo
import androidx.core.content.ContextCompat
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.learnandroid.R
import com.example.learnandroid.databinding.FragmentOnboardingHeightBinding
import com.example.learnandroid.presentation.screens.base.BaseViewBindingFragment
import com.example.learnandroid.utils.constants.AppConstants
import com.example.learnandroid.utils.extensions.focus
import com.example.learnandroid.utils.extensions.unFocus
import kotlinx.coroutines.launch

class OnboardingHeightFragment :
    BaseViewBindingFragment<FragmentOnboardingHeightBinding, OnboardingHeightViewModel>(
        FragmentOnboardingHeightBinding::inflate
    ) {
    override val viewModel: OnboardingHeightViewModel by viewModels()

    interface OnboardingHeightDelegate {
        fun didSelectHeight(height: Int)
    }

    private var delegate: OnboardingHeightDelegate? = null

    companion object {
        fun newInstance(): OnboardingHeightFragment {
            return OnboardingHeightFragment()
        }
    }

    fun setAction(delegate: OnboardingHeightDelegate) {
        this.delegate = delegate
    }

    override fun setup() {
        super.setup()
        setupUI()
        setupBinding()
    }

    private fun setupUI() {
        viewBinding.apply {
            heightTextField.setHintTextColor(
                ContextCompat.getColor(
                    requireActivity(),
                    R.color.blue_base
                )
            )
            heightTextField.addTextChangedListener { editable ->
                editable?.let {
                    val newText = it.toString()
                    val intValue = newText.toIntOrNull()

                    if (newText.isEmpty() || (intValue != null && intValue > 0)) {
                        viewModel.setHeight(intValue)
                    } else {
                        heightTextField.text = null
                    }
                } ?: run {
                    // If the text is null, update the view model with null
                    viewModel.setHeight(null)
                }
            }

            heightTextField.setOnEditorActionListener { v, actionId, event ->
                when (actionId) {
                    EditorInfo.IME_ACTION_DONE -> {
                        viewModel.height.value?.let {
                            delegate?.didSelectHeight(it)
                        }
                        return@setOnEditorActionListener true
                    }

                    else -> false
                }
            }

            continueButton.setOnClickListener {
                viewModel.height.value?.let {
                    delegate?.didSelectHeight(it)
                }
            }
        }
    }

    private fun setupBinding() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.height.collect { height ->
                val isEnabled = height?.let {
                    it in AppConstants.MIN_HEIGHT..AppConstants.MAX_HEIGHT
                } ?: run {
                    false
                }
                viewBinding.continueButton.isEnabled = isEnabled
                viewBinding.continueButton.alpha = if (isEnabled) 1f else 0.3f
            }
        }
    }

    fun resetData() {
        viewBinding.heightTextField.text = null
    }

    override fun onResume() {
        super.onResume()
        viewBinding.heightTextField.focus(requireActivity())
    }

    override fun onPause() {
        super.onPause()
        viewBinding.heightTextField.unFocus(requireActivity())
    }

    override fun onDestroyView() {
        super.onDestroyView()
        delegate = null
    }

    override fun onDestroy() {
        super.onDestroy()
        delegate = null
    }
}