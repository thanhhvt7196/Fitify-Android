package com.example.learnandroid.presentation.screens.onboarding.salePitch

import android.text.Html
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.learnandroid.databinding.FragmentOnboardingSalePitchBinding
import com.example.learnandroid.domain.models.Gender
import com.example.learnandroid.domain.models.OnboardingGoal
import com.example.learnandroid.presentation.screens.base.BaseViewBindingFragment
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.mapNotNull
import kotlinx.coroutines.launch

interface OnboardingSalePitchDelegate {
    fun didContinueTapped()
}

class OnboardingSalePitchFragment :
    BaseViewBindingFragment<FragmentOnboardingSalePitchBinding, OnboardingSalePitchViewModel>(
        FragmentOnboardingSalePitchBinding::inflate
    ) {

    private var delegate: OnboardingSalePitchDelegate? = null

    override val viewModel: OnboardingSalePitchViewModel by viewModels()

    private val _goal = MutableStateFlow<OnboardingGoal?>(null)
    private val _gender = MutableStateFlow<Gender?>(null)

    companion object {
        const val tag = "OnboardingSalePitchFragment"
        fun newInstance(): OnboardingSalePitchFragment {
            return OnboardingSalePitchFragment()
        }
    }

    override fun setup() {
        super.setup()
        setupUI()
        setupBinding()
    }

    private fun setupUI() {
        viewBinding.apply {
            continueButton.setOnClickListener {
                delegate?.didContinueTapped()
            }
        }
    }

    private fun setupBinding() {
        viewLifecycleOwner.lifecycleScope.launch {
            _goal.mapNotNull { it }
                .combine(_gender.mapNotNull { it }) { newGoal, newGender ->
                    return@combine Pair(newGoal, newGender)
                }
                .collect { (newGoal, newGender) ->
                    val title = newGoal.getSalePitchTitle(requireActivity(), newGender)
                    viewBinding.titleTextView.text = Html.fromHtml(title, Html.FROM_HTML_MODE_LEGACY)

                    viewBinding.imageView.setImageResource(
                        newGoal.getSalePicthImageResource(
                            requireActivity(),
                            newGender
                        )
                    )
                    viewBinding.messageTextView.text =
                        newGoal.getSalePitchMessage(requireActivity(), newGender)
                }
        }
    }

    fun setGoal(goal: OnboardingGoal) {
        _goal.value = goal
    }

    fun setGender(gender: Gender) {
        _gender.value = gender
    }

    fun setAction(delegate: OnboardingSalePitchDelegate) {
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
}