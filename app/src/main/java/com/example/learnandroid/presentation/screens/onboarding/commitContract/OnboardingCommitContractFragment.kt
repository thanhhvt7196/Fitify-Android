package com.example.learnandroid.presentation.screens.onboarding.commitContract

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.animation.PropertyValuesHolder
import android.annotation.SuppressLint
import android.os.Handler
import android.os.Looper
import android.text.Html
import android.view.MotionEvent
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.learnandroid.R
import com.example.learnandroid.databinding.FragmentOnboardingCommitContractBinding
import com.example.learnandroid.presentation.components.circleGradientBackground.CircleGradientBackground
import com.example.learnandroid.presentation.screens.base.BaseViewBindingFragment
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class OnboardingCommitContractFragment :
    BaseViewBindingFragment<FragmentOnboardingCommitContractBinding, OnboardingCommitContractViewModel>(
        FragmentOnboardingCommitContractBinding::inflate
    ) {
    override val viewModel: OnboardingCommitContractViewModel by viewModels()
    private val handler = Handler(Looper.getMainLooper())
    private val commitTextDelay = 800L
    private lateinit var pulsatingViewScaleAnimator: ObjectAnimator

    private val commitTextRunnable = object : Runnable {
        override fun run() {
            toggleCommitText()
            handler.postDelayed(this, commitTextDelay)
        }
    }

    private var isAnimationCancelled = false

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

    @SuppressLint("ClickableViewAccessibility")
    private fun setupPulsatingView() {
        viewBinding.apply {
            pulsatingView.background = CircleGradientBackground(requireActivity())
            pulsatingOuterView.background = CircleGradientBackground(requireActivity())

            pulsatingView.setOnTouchListener { view, event ->
                when (event.action) {
                    MotionEvent.ACTION_DOWN -> {
                        scheduleTimer()
                        isAnimationCancelled = false
                        hintTextView.visibility = View.INVISIBLE
                        pulsatingOuterBackgroundView.isVisible = false
                        pulsatingOuterView.isVisible = false
                        startScaleAnimation(view)
                        return@setOnTouchListener true
                    }

                    MotionEvent.ACTION_UP -> {
                        endAnimation()
                        return@setOnTouchListener true
                    }

                    MotionEvent.ACTION_CANCEL -> {
                        endAnimation()
                        return@setOnTouchListener true
                    }

                    else -> {
                        return@setOnTouchListener false
                    }
                }
            }

            pulsatingView

            startPulseAnimation(pulsatingOuterBackgroundView)
            startPulseAnimation(pulsatingOuterView)
        }
    }

    private fun startScaleAnimation(view: View) {
        pulsatingViewScaleAnimator = ObjectAnimator.ofPropertyValuesHolder(
            view,
            PropertyValuesHolder.ofFloat(View.SCALE_X.name, 7f),
            PropertyValuesHolder.ofFloat(View.SCALE_Y.name, 7f)
        )
        pulsatingViewScaleAnimator.duration = 3000
        pulsatingViewScaleAnimator.start()
    }

    private fun startPulseAnimation(view: View) {
        val animatorSet = AnimatorSet()

        val pulseAnimator = ObjectAnimator.ofPropertyValuesHolder(
            view,
            PropertyValuesHolder.ofFloat(View.SCALE_X.name, 1.15f),
            PropertyValuesHolder.ofFloat(View.SCALE_Y.name, 1.15f)
        )
        pulseAnimator.duration = 2000
        pulseAnimator.repeatCount = ObjectAnimator.INFINITE
        pulseAnimator.repeatMode = ObjectAnimator.RESTART

        val alphaAnimator = ObjectAnimator.ofFloat(view, View.ALPHA, 1f, 0f)
        alphaAnimator.duration = 2000
        alphaAnimator.repeatCount = ObjectAnimator.INFINITE
        alphaAnimator.repeatMode = ObjectAnimator.RESTART

        animatorSet.playTogether(alphaAnimator, pulseAnimator)
        animatorSet.start()
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
                viewBinding.apply {
                    commitTextView.isVisible = state != OnboardingCommitContractViewModel.CommitState.NONE
                    commitTextView.text = state.getTitle(requireActivity())
                    checkImageView.isVisible = state == OnboardingCommitContractViewModel.CommitState.AWESOME
                }
            }
        }
    }

    private fun scheduleTimer() {
        handler.postDelayed(commitTextRunnable, commitTextDelay)
    }

    private fun endAnimation() {
        pulsatingViewScaleAnimator.cancel()
        handler.removeCallbacks(commitTextRunnable)
        viewModel.setState(OnboardingCommitContractViewModel.CommitState.NONE)

        val pulseAnimator = ObjectAnimator.ofPropertyValuesHolder(
            viewBinding.pulsatingView,
            PropertyValuesHolder.ofFloat(View.SCALE_X.name, 1f),
            PropertyValuesHolder.ofFloat(View.SCALE_Y.name, 1f)
        )
        pulseAnimator.duration = 100
        pulseAnimator.addListener(object : AnimatorListenerAdapter() {
            override fun onAnimationEnd(animation: Animator) {
                viewBinding.apply {
                    pulsatingOuterView.isVisible = true
                    pulsatingOuterBackgroundView.isVisible = true
                    viewModel.setState(OnboardingCommitContractViewModel.CommitState.NONE)
                    hintTextView.visibility = View.VISIBLE
                }
            }
        })
        pulseAnimator.start()
    }

    private fun toggleCommitText() {
        lifecycleScope.launch (Dispatchers.IO) {
            val allStates = OnboardingCommitContractViewModel.CommitState.values()
            val currentState = viewModel.state.value

            if (currentState == OnboardingCommitContractViewModel.CommitState.NONE) {
                viewModel.setState(OnboardingCommitContractViewModel.CommitState.HOLD_IT)
            } else {
                val index = allStates.indexOf(currentState)
                viewModel.setState(
                    if (index + 1 < allStates.count()) allStates[index + 1]
                    else OnboardingCommitContractViewModel.CommitState.NONE
                )
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

    override fun onPause() {
        super.onPause()
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