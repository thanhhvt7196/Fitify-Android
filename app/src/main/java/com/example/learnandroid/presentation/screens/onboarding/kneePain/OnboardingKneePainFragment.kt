package com.example.learnandroid.presentation.screens.onboarding.kneePain

import android.app.Dialog
import android.view.View
import android.view.Window
import android.view.animation.AlphaAnimation
import android.view.animation.Animation
import android.view.animation.AnimationSet
import android.view.animation.TranslateAnimation
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.learnandroid.R
import com.example.learnandroid.databinding.FragmentOnboardingKneePainBinding
import com.example.learnandroid.domain.models.KneePain
import com.example.learnandroid.presentation.screens.base.BaseViewBindingFragment
import kotlinx.coroutines.launch

class OnboardingKneePainFragment :
    BaseViewBindingFragment<FragmentOnboardingKneePainBinding, OnboardingKneePainViewModel>(
        FragmentOnboardingKneePainBinding::inflate
    ) {
    override val viewModel: OnboardingKneePainViewModel by viewModels()
    private var dialog: Dialog? = null

    interface OnboardingKneePainDelegate {
        fun didSelectKneePain(kneePain: KneePain)
    }

    private var delegate: OnboardingKneePainDelegate? = null

    companion object {
        const val tag = "OnboardingKneePainFragment"
        fun newInstance(): OnboardingKneePainFragment {
            return OnboardingKneePainFragment()
        }
    }

    override fun setup() {
        super.setup()
        setupUI()
        setupBinding()
    }

    private fun setupUI() {
        viewBinding.apply {
            noButton.config(requireActivity().getString(R.string.no), null)
            yesButton.config(requireActivity().getString(R.string.yes), null)
            painOptionsContainerView.isVisible = false

            noButton.setOnClickListener {
                viewModel.setKneePain(KneePain.NO)
                yesButton.isSelected = false
                hideOptionsWithAnimation()
            }

            yesButton.setOnClickListener {
                yesButton.isSelected = true
                if (noButton.isSelected) {
                    viewModel.setKneePain(null)
                }
                if (!painOptionsContainerView.isVisible) {
                    showOptionsWithAnimation()
                }
            }

            mildPainButton.config(
                requireActivity().getString(R.string.knee_pain_mild),
                requireActivity().getString(R.string.knee_pain_mild_description)
            )

            mildPainButton.setOnClickListener {
                showKneePainAlert(KneePain.MILD_PAIN)
                yesButton.isSelected = true
            }

            seriousButton.config(
                requireActivity().getString(R.string.knee_pain_serious),
                requireActivity().getString(R.string.knee_pain_serious_description)
            )

            seriousButton.setOnClickListener {
                showKneePainAlert(KneePain.QUITE_SERIOUS)
                yesButton.isSelected = true
            }
        }
    }

    private fun setupBinding() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.kneePain.collect { kneePain ->
                viewBinding.apply {
                    noButton.isSelected = kneePain == KneePain.NO
                    mildPainButton.isSelected = kneePain == KneePain.MILD_PAIN
                    seriousButton.isSelected = kneePain == KneePain.QUITE_SERIOUS
                }
                kneePain?.let {
                    delegate?.didSelectKneePain(kneePain)
                }
            }
        }
    }

    private fun showKneePainAlert(kneePain: KneePain) {
        dialog = Dialog(requireActivity())
        dialog?.window?.setBackgroundDrawableResource(android.R.color.transparent)
        dialog?.requestWindowFeature(Window.FEATURE_NO_TITLE)
        val kneePainAlertView = KneePainAlert(requireActivity(), null)
        val delegate = object : KneePainAlert.KneePainAlertDelegate {
            override fun didContinueTapped() {
                dialog?.dismiss()
                viewModel.setKneePain(kneePain)
                dialog = null
            }
        }
        kneePainAlertView.setAction(delegate)
        dialog?.setContentView(kneePainAlertView)
        dialog?.setCancelable(false)
        dialog?.setCanceledOnTouchOutside(false)
        dialog?.show()
    }

    fun resetData() {
        viewModel.setKneePain(null)
        viewBinding.apply {
            yesButton.isSelected = false
            hideOptionsWithAnimation()
        }
    }

    fun setAction(delegate: OnboardingKneePainDelegate) {
        this.delegate = delegate
    }

    override fun onDestroyView() {
        super.onDestroyView()
        delegate = null
    }

    override fun onDestroy() {
        super.onDestroy()
        delegate = null
    }

    private fun showOptionsWithAnimation() {
        viewBinding.painOptionsContainerView.isVisible = true
        val fadeAnimation = AlphaAnimation(0f, 1f)
        val slideAnimation = TranslateAnimation(0f, 0f, -viewBinding.painOptionsContainerView.height.toFloat(), 0f)
        val animationSet = AnimationSet(true)
        animationSet.addAnimation(fadeAnimation)
        animationSet.addAnimation(slideAnimation)
        animationSet.duration = 300
        viewBinding.painOptionsContainerView.startAnimation(animationSet)
    }

    private fun hideOptionsWithAnimation() {
        val animationSet = AnimationSet(true)
        val fadeAnimation = AlphaAnimation(1f, 0f)
        val slideAnimation = TranslateAnimation(0f, 0f, 0f, -viewBinding.painOptionsContainerView.height.toFloat())
        animationSet.duration = 300 // Animation duration in milliseconds
        animationSet.addAnimation(slideAnimation)
        animationSet.addAnimation(fadeAnimation)
        animationSet.setAnimationListener(object : Animation.AnimationListener {
            override fun onAnimationStart(animation: Animation) {}

            override fun onAnimationEnd(animation: Animation) {
                viewBinding.painOptionsContainerView.isVisible = false
            }

            override fun onAnimationRepeat(animation: Animation) {}
        })

        viewBinding.painOptionsContainerView.startAnimation(animationSet)
    }
}