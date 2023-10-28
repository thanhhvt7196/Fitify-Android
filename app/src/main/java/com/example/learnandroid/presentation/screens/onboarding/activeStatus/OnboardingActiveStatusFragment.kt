package com.example.learnandroid.presentation.screens.onboarding.activeStatus

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import androidx.core.content.ContextCompat
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.learnandroid.R
import com.example.learnandroid.databinding.FragmentOnboardingActiveStatusBinding
import com.example.learnandroid.domain.models.ActiveStatus
import com.example.learnandroid.presentation.screens.base.BaseViewBindingFragment
import com.example.learnandroid.presentation.screens.onboarding.age.OnboardingAgeFragment
import com.example.learnandroid.utils.constants.AppConstants
import com.example.learnandroid.utils.extensions.firstCapitalize
import com.example.learnandroid.utils.extensions.focus
import com.example.learnandroid.utils.extensions.unFocus
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
        const val tag = "OnboardingActiveStatusFragment"
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
                delegate?.didSelectActiveStatus(ActiveStatus.ACTIVE)
            }

            onFootButton.config(ActiveStatus.ON_FOOT.getTitle(requireActivity()))
            onFootButton.setOnClickListener {
                viewModel.setActiveStatus(ActiveStatus.ON_FOOT)
                delegate?.didSelectActiveStatus(ActiveStatus.ON_FOOT)
            }

            seatedButton.config(ActiveStatus.SEATED.getTitle(requireActivity()))
            seatedButton.setOnClickListener {
                viewModel.setActiveStatus(ActiveStatus.SEATED)
                delegate?.didSelectActiveStatus(ActiveStatus.SEATED)
            }

            inactiveButton.config(ActiveStatus.INACTIVE.getTitle(requireActivity()))
            inactiveButton.setOnClickListener {
                viewModel.setActiveStatus(ActiveStatus.INACTIVE)
                delegate?.didSelectActiveStatus(ActiveStatus.INACTIVE)
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