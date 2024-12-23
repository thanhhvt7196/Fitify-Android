package com.example.fitifyandroid.presentation.screens.onboarding.pushup


import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.fitifyandroid.databinding.FragmentOnboardingPushUpBinding
import com.example.fitifyandroid.domain.models.PushUp
import com.example.fitifyandroid.presentation.screens.base.BaseViewBindingFragment
import kotlinx.coroutines.launch

class OnboardingPushUpFragment :
    BaseViewBindingFragment<FragmentOnboardingPushUpBinding, OnboardingPushUpViewModel>(
        FragmentOnboardingPushUpBinding::inflate
    ) {

    override val viewModel: OnboardingPushUpViewModel by viewModels()

    interface OnboardingPushUpDelegate {
        fun didSelectPushUp(pushUp: PushUp)
    }

    private var delegate: OnboardingPushUpDelegate? = null

    companion object {
        fun newInstance(): OnboardingPushUpFragment {
            return OnboardingPushUpFragment()
        }
    }

    fun setAction(delegate: OnboardingPushUpDelegate) {
        this.delegate = delegate
    }

    override fun setup() {
        super.setup()
        setupUI()
        setupBinding()
    }

    private fun setupUI() {
        viewBinding.apply {
            moreThan30Button.isSelected = false
            moreThan15Button.isSelected = false
            moreThan6Button.isSelected = false
            lessThan5Button.isSelected = false

            moreThan30Button.config(PushUp.MORE_THAN_30.getTitle(requireActivity()))
            moreThan30Button.setOnClickListener {
                viewModel.setPushUp(PushUp.MORE_THAN_30)
            }

            moreThan15Button.config(PushUp.FIFTEEN_TO_TWENTY_NINE.getTitle(requireActivity()))
            moreThan15Button.setOnClickListener {
                viewModel.setPushUp(PushUp.FIFTEEN_TO_TWENTY_NINE)
            }

            moreThan6Button.config(PushUp.SIX_TO_FOURTEEN.getTitle(requireActivity()))
            moreThan6Button.setOnClickListener {
                viewModel.setPushUp(PushUp.SIX_TO_FOURTEEN)
            }

            lessThan5Button.config(PushUp.LESS_THAN_FIVE.getTitle(requireActivity()))
            lessThan5Button.setOnClickListener {
                viewModel.setPushUp(PushUp.LESS_THAN_FIVE)
            }
        }
    }

    private fun setupBinding() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.pushUp.collect { pushUp ->
                viewBinding.apply {
                    moreThan30Button.isSelected = pushUp == PushUp.MORE_THAN_30
                    moreThan15Button.isSelected = pushUp == PushUp.FIFTEEN_TO_TWENTY_NINE
                    moreThan6Button.isSelected = pushUp == PushUp.SIX_TO_FOURTEEN
                    lessThan5Button.isSelected = pushUp == PushUp.LESS_THAN_FIVE
                }
                pushUp?.let {
                    delegate?.didSelectPushUp(it)
                }
            }
        }
    }

    fun resetData() {
        viewModel.setPushUp(null)
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