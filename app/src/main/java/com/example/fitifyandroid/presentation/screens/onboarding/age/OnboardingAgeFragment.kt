package com.example.fitifyandroid.presentation.screens.onboarding.age

import android.view.inputmethod.EditorInfo
import androidx.core.content.ContextCompat
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.fitifyandroid.R
import com.example.fitifyandroid.databinding.FragmentOnboardingAgeBinding
import com.example.fitifyandroid.presentation.screens.base.BaseViewBindingFragment
import com.example.fitifyandroid.utils.constants.AppConstants
import com.example.fitifyandroid.utils.extensions.firstCapitalize
import com.example.fitifyandroid.utils.extensions.focus
import com.example.fitifyandroid.utils.extensions.unFocus
import kotlinx.coroutines.launch


class OnboardingAgeFragment :
    BaseViewBindingFragment<FragmentOnboardingAgeBinding, OnboardingAgeViewModel>(
        FragmentOnboardingAgeBinding::inflate
    ) {
    override val viewModel: OnboardingAgeViewModel by viewModels()

    interface OnboardingAgeDelegate {
        fun didSelectAge(age: Int)
    }

    private var delegate: OnboardingAgeDelegate? = null

    companion object {
        fun newInstance(): OnboardingAgeFragment {
            return OnboardingAgeFragment()
        }
    }

    fun setAction(delegate: OnboardingAgeDelegate) {
        this.delegate = delegate
    }

    override fun setup() {
        super.setup()
        setupUI()
        setupBinding()
    }

    private fun setupUI() {
        viewBinding.apply {
            unitTextView.text = requireActivity().getString(R.string.unit_years).firstCapitalize()
            ageEditText.setHintTextColor(
                ContextCompat.getColor(
                    requireActivity(),
                    R.color.blue_base
                )
            )
            ageEditText.addTextChangedListener { editable ->
                editable?.let {
                    val newText = it.toString()
                    val intValue = newText.toIntOrNull()

                    if (newText.isEmpty() || (intValue != null && intValue > 0)) {
                        viewModel.setAge(intValue)
                    } else {
                        ageEditText.text = null
                    }
                } ?: run {
                    // If the text is null, update the view model with null
                    viewModel.setAge(null)
                }
            }

            ageEditText.setOnEditorActionListener { v, actionId, event ->
                when (actionId) {
                    EditorInfo.IME_ACTION_DONE -> {
                        viewModel.age.value?.let {
                            delegate?.didSelectAge(it)
                        }
                        return@setOnEditorActionListener true
                    }

                    else -> false
                }
            }

            continueButton.setOnClickListener {
                viewModel.age.value?.let {
                    delegate?.didSelectAge(it)
                }
            }
        }
    }

    private fun setupBinding() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.age.collect { age ->
                val isEnabled = age?.let {
                    it in AppConstants.MIN_AGE..AppConstants.MAX_AGE
                } ?: run {
                    false
                }
                viewBinding.continueButton.isEnabled = isEnabled
                viewBinding.continueButton.alpha = if (isEnabled) 1f else 0.3f
            }
        }
    }

    fun resetData() {
        viewBinding.ageEditText.text = null
    }

    override fun onResume() {
        super.onResume()
        viewBinding.ageEditText.focus(requireActivity())
    }

    override fun onPause() {
        super.onPause()
        viewBinding.ageEditText.unFocus(requireActivity())
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