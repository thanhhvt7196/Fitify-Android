package com.example.learnandroid.presentation.screens.onboarding.activeStatus

import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.learnandroid.databinding.FragmentOnboardingActiveStatusBinding
import com.example.learnandroid.domain.models.ActiveStatus
import com.example.learnandroid.presentation.screens.base.BaseViewBindingFragment
import kotlinx.coroutines.launch

class OnboardingActiveStatusFragment :
    BaseViewBindingFragment<FragmentOnboardingActiveStatusBinding, OnboardingActiveStatusViewModel>(
        FragmentOnboardingActiveStatusBinding::inflate
    ) {
    override val viewModel: OnboardingActiveStatusViewModel by viewModels()

    interface OnboardingActiveStatusDelegate {
        fun didSelectActiveStatus(status: ActiveStatus)
    }

    private var delegate: OnboardingActiveStatusDelegate? = null

    companion object {
        fun newInstance(): OnboardingActiveStatusFragment {
            return OnboardingActiveStatusFragment()
        }
    }

    fun setAction(delegate: OnboardingActiveStatusDelegate) {
        this.delegate = delegate
    }

    override fun setup() {
        super.setup()
        setupUI()
        setupBinding()
    }

    private fun setupUI() {
        viewBinding.apply {
            activeButton.isSelected = false
            onFootButton.isSelected = false
            seatedButton.isSelected = false
            inactiveButton.isSelected = false

            activeButton.config(ActiveStatus.ACTIVE.getTitle(requireActivity()))
            activeButton.setOnClickListener {
                viewModel.setActiveStatus(ActiveStatus.ACTIVE)
            }

            onFootButton.config(ActiveStatus.ON_FOOT.getTitle(requireActivity()))
            onFootButton.setOnClickListener {
                viewModel.setActiveStatus(ActiveStatus.ON_FOOT)
            }

            seatedButton.config(ActiveStatus.SEATED.getTitle(requireActivity()))
            seatedButton.setOnClickListener {
                viewModel.setActiveStatus(ActiveStatus.SEATED)
            }

            inactiveButton.config(ActiveStatus.INACTIVE.getTitle(requireActivity()))
            inactiveButton.setOnClickListener {
                viewModel.setActiveStatus(ActiveStatus.INACTIVE)
            }
        }
    }

    private fun setupBinding() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.activeStatus.collect { status ->
                viewBinding.apply {
                    activeButton.isSelected = status == ActiveStatus.ACTIVE
                    onFootButton.isSelected = status == ActiveStatus.ON_FOOT
                    seatedButton.isSelected = status == ActiveStatus.SEATED
                    inactiveButton.isSelected = status == ActiveStatus.INACTIVE
                }
                status?.let {
                    delegate?.didSelectActiveStatus(it)
                }
            }
        }
    }

    fun resetData() {
        viewModel.setActiveStatus(null)
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