package com.example.learnandroid.presentation.screens.onboarding.commitContract

import android.animation.ObjectAnimator
import android.animation.PropertyValuesHolder
import android.animation.ValueAnimator
import android.graphics.drawable.GradientDrawable
import android.text.Html
import androidx.core.content.ContextCompat
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.learnandroid.R
import com.example.learnandroid.databinding.FragmentOnboardingCommitContractBinding
import com.example.learnandroid.presentation.screens.base.BaseViewBindingFragment
import kotlinx.coroutines.launch

class OnboardingCommitContractFragment :
    BaseViewBindingFragment<FragmentOnboardingCommitContractBinding, OnboardingCommitContractViewModel>(
        FragmentOnboardingCommitContractBinding::inflate
    ) {
    override val viewModel: OnboardingCommitContractViewModel by viewModels()

    private val scaleRatio = 1.5f
    private val isAnimationCancelled = false

    interface OnboardingCommitContractDelegate {
        fun didCommitted()
    }

    private var delegate: OnboardingCommitContractDelegate? = null

    companion object {
        const val tag = "OnboardingCommitContractFragment"
        fun newInstance(): OnboardingCommitContractFragment {
            return OnboardingCommitContractFragment()
        }
    }

    override fun setup() {
        super.setup()
        setupUI()
        setupBinding()
    }

    private fun setupPulsatingView() {
        viewBinding.apply {
            val gradientDrawable = GradientDrawable(
                GradientDrawable.Orientation.TOP_BOTTOM,
                intArrayOf(
                    ContextCompat.getColor(
                        requireActivity(),
                        R.color.vibrant_gradient_start
                    ), ContextCompat.getColor(requireActivity(), R.color.vibrant_gradient_end)
                )
            )
            gradientDrawable.shape = GradientDrawable.OVAL
            pulsatingView.background = gradientDrawable

            pulsatingView.setOnLongClickListener {
                true
            }

            val pulseAnimator = ObjectAnimator.ofPropertyValuesHolder(
                pulsatingOuterView,
                PropertyValuesHolder.ofFloat("scaleX", 120f),
                PropertyValuesHolder.ofFloat("scaleY", 120f)
            )
            pulseAnimator.duration = 3000
            pulseAnimator.repeatCount = ObjectAnimator.INFINITE
            pulseAnimator.repeatMode = ValueAnimator.RESTART
            pulseAnimator.start()
        }
    }

    private fun setupUI() {
        setupPulsatingView()
        viewBinding.apply {
            val title = requireActivity().getString(R.string.onboarding_commit_contract_message)
            messageTextView.text = Html.fromHtml(title, Html.FROM_HTML_MODE_LEGACY)
        }
    }

    private fun setupBinding() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.state.collect { state ->

            }
        }
    }

    fun setAction(delegate: OnboardingCommitContractDelegate) {
        this.delegate = delegate
    }

    override fun onResume() {
        super.onResume()
        viewModel.setState(OnboardingCommitContractViewModel.CommitState.NONE)
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