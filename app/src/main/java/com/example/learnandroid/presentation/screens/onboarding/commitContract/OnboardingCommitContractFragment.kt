package com.example.learnandroid.presentation.screens.onboarding.commitContract

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.animation.PropertyValuesHolder
import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.ColorFilter
import android.graphics.Paint
import android.graphics.Path
import android.graphics.PixelFormat
import android.graphics.PorterDuff
import android.graphics.PorterDuffXfermode
import android.graphics.RadialGradient
import android.graphics.Shader
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.graphics.drawable.LayerDrawable
import android.os.Handler
import android.os.Looper
import android.renderscript.Allocation
import android.renderscript.Element
import android.renderscript.RenderScript
import android.renderscript.ScriptIntrinsicBlur
import android.text.Html
import android.util.AttributeSet
import android.util.Log
import android.view.MotionEvent
import android.view.View
import android.widget.Toast
import androidx.core.animation.addListener
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.learnandroid.LoginActivity
import com.example.learnandroid.R
import com.example.learnandroid.databinding.FragmentOnboardingCommitContractBinding
import com.example.learnandroid.presentation.components.circleGradientBackground.CircleGradientBackground
import com.example.learnandroid.presentation.screens.base.BaseViewBindingFragment
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.Timer

class OnboardingCommitContractFragment :
    BaseViewBindingFragment<FragmentOnboardingCommitContractBinding, OnboardingCommitContractViewModel>(
        FragmentOnboardingCommitContractBinding::inflate
    ) {
    enum class CommitState: Iterable<CommitState> {
        NONE {
            override fun getTitle(context: Context): String = ""
        },
        HOLD_IT {
            override fun getTitle(context: Context): String = context.getString(R.string.onboarding_commit_contract_hold_it)
        },
        KEEP_GOING {
            override fun getTitle(context: Context): String = context.getString(R.string.onboarding_commit_contract_keep_going)
        },
        AWESOME {
            override fun getTitle(context: Context): String = context.getString(R.string.onboarding_commit_contract_awesome)
        };

        abstract fun getTitle(context: Context): String

        override fun iterator(): Iterator<CommitState> = enumValues<CommitState>().iterator()
    }

    override val viewModel: OnboardingCommitContractViewModel by viewModels()
    private val handler = Handler(Looper.getMainLooper())
    private val commitTextDelay = 800L
    private var pulsatingViewScaleAnimator = ObjectAnimator()

    private val commitTextRunnable = object : Runnable {
        override fun run() {
            toggleCommitText()
            handler.postDelayed(this, commitTextDelay)
        }
    }

    private var isAnimationCancelled = false
    private var state = CommitState.NONE

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

            startPulseAnimation(pulsatingOuterBackgroundView)
            startPulseAnimation(pulsatingOuterView)
        }
    }

    private fun startScaleAnimation(view: View) {
        isAnimationCancelled = false
        pulsatingViewScaleAnimator.removeAllListeners()
        pulsatingViewScaleAnimator = ObjectAnimator.ofPropertyValuesHolder(
            view,
            PropertyValuesHolder.ofFloat(View.SCALE_X.name, 10f),
            PropertyValuesHolder.ofFloat(View.SCALE_Y.name, 10f)
        )
        pulsatingViewScaleAnimator.duration = 3000

        pulsatingViewScaleAnimator.addListener(object : AnimatorListenerAdapter() {
            override fun onAnimationEnd(animation: Animator) {
                state = CommitState.NONE
                viewBinding.apply {
                    if (!isAnimationCancelled) {
                        delegate?.didCommitted()
                    }
                }
            }
        })
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

    private fun scheduleTimer() {
        handler.postDelayed(commitTextRunnable, commitTextDelay)
    }

    private fun endAnimation() {
        isAnimationCancelled = true
        pulsatingViewScaleAnimator.cancel()
        handler.removeCallbacks(commitTextRunnable)
        state = CommitState.NONE
        viewBinding.commitTextView.text = ""
        viewBinding.checkImageView.isVisible = false
        viewBinding.hintTextView.visibility = View.VISIBLE


        val pulseAnimator = ObjectAnimator.ofPropertyValuesHolder(
            viewBinding.pulsatingView,
            PropertyValuesHolder.ofFloat(View.SCALE_X.name, 1f),
            PropertyValuesHolder.ofFloat(View.SCALE_Y.name, 1f)
        )
        pulseAnimator.duration = 100
        pulseAnimator.addListener(object : AnimatorListenerAdapter() {
            override fun onAnimationEnd(animation: Animator) {
                state = CommitState.NONE
                viewBinding.apply {
                    pulsatingOuterView.isVisible = true
                    pulsatingOuterBackgroundView.isVisible = true
                }
            }
        })
        pulseAnimator.start()
    }

    private fun toggleCommitText() {
        val allStates = CommitState.values()

        val index = allStates.indexOf(state)
        state = if (index + 1 < allStates.count()) allStates[index + 1] else CommitState.NONE
        viewBinding.commitTextView.text = state.getTitle(requireActivity())
        viewBinding.checkImageView.isVisible = state == CommitState.AWESOME
    }

    fun setAction(delegate: OnboardingCommitContractDelegate) {
        this.delegate = delegate
    }

    override fun onResume() {
        super.onResume()
        state = CommitState.NONE
        viewBinding.commitTextView.text = ""
        endAnimation()
    }

    override fun onPause() {
        super.onPause()
        state = CommitState.NONE
        viewBinding.commitTextView.text = ""
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