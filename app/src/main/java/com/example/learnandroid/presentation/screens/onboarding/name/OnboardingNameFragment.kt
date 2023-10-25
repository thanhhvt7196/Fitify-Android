package com.example.learnandroid.presentation.screens.onboarding.name

import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.learnandroid.databinding.FragmentOnboardingNameBinding
import com.example.learnandroid.presentation.screens.base.BaseViewBindingFragment
import com.example.learnandroid.utils.extensions.focus
import com.example.learnandroid.utils.extensions.unfocus
import kotlinx.coroutines.launch

class OnboardingNameFragment :
    BaseViewBindingFragment<FragmentOnboardingNameBinding, OnboardingNameViewModel>(
        FragmentOnboardingNameBinding::inflate
    ) {
    override val viewModel: OnboardingNameViewModel by viewModels()

    interface OnboardingNameDelegate {
        fun didSelectName(name: String)
    }

    private var delegate: OnboardingNameDelegate? = null

    companion object {
        const val tag = "OnboardingNameFragment"
        fun newInstance(): OnboardingNameFragment {
            return OnboardingNameFragment()
        }
    }

    fun setAction(delegate: OnboardingNameDelegate) {
        this.delegate = delegate
    }

    override fun setup() {
        super.setup()
        setupUI()
        setupBinding()
    }

    private fun setupUI() {
        viewBinding.apply {
            nameTextView.addTextChangedListener { editable ->
                editable?.let {
                    viewModel.setName(it.toString())
                } ?: run {
                    viewModel.setName("")
                }
            }

            continueButton.setOnClickListener {
                delegate?.didSelectName(viewModel.name.value)
            }
        }
    }

    private fun setupBinding() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.name.collect { name ->
                val isEnabled = name.length >= 3
                viewBinding.continueButton.isEnabled = isEnabled
                viewBinding.continueButton.alpha = if (isEnabled) 1f else 0.3f
            }
        }
    }

    fun resetData() {
        viewBinding.nameTextView.setText("")
    }

    override fun onResume() {
        super.onResume()
        viewBinding.nameTextView.focus(requireActivity())
    }

    override fun onPause() {
        super.onPause()
        viewBinding.nameTextView.unfocus(requireActivity())
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