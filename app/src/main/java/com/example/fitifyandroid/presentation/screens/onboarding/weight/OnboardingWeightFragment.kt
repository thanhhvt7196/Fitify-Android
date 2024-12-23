package com.example.fitifyandroid.presentation.screens.onboarding.weight

import android.text.InputFilter
import android.text.Spanned
import android.view.inputmethod.EditorInfo
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.fitifyandroid.R
import com.example.fitifyandroid.databinding.FragmentOnboardingWeightBinding
import com.example.fitifyandroid.presentation.screens.base.BaseViewBindingFragment
import com.example.fitifyandroid.utils.constants.AppConstants
import com.example.fitifyandroid.utils.extensions.countDecimalPlaces
import com.example.fitifyandroid.utils.extensions.focus
import com.example.fitifyandroid.utils.extensions.isFloat
import com.example.fitifyandroid.utils.extensions.roundTo
import com.example.fitifyandroid.utils.extensions.toStringWithoutTrailingZero
import com.example.fitifyandroid.utils.extensions.unFocus
import kotlinx.coroutines.launch


class OnboardingWeightFragment :
    BaseViewBindingFragment<FragmentOnboardingWeightBinding, OnboardingWeightViewModel>(
        FragmentOnboardingWeightBinding::inflate
    ) {
    override val viewModel: OnboardingWeightViewModel by viewModels()
    private var hintWeight: Float? = null

    enum class Type {
        CURRENT_WEIGHT,
        TARGET_WEIGHT
    }

    companion object {
        fun currentWeightNewInstance(): OnboardingWeightFragment {
            return OnboardingWeightFragment()
        }

        fun targetWeightNewInstance(): OnboardingWeightFragment {
            return OnboardingWeightFragment()
        }
    }

    interface OnboardingWeightDelegate {
        fun didSelectWeight(weight: Float)
    }

    private var delegate: OnboardingWeightDelegate? = null

    fun setAction(delegate: OnboardingWeightDelegate) {
        this.delegate = delegate
    }

    override fun setup() {
        super.setup()
        setupUI()
        setupBinding()
    }

    private fun setupUI() {
        viewBinding.apply {
            arguments?.getString("type")?.let { type ->
                when (type) {
                    Type.TARGET_WEIGHT.name -> {
                        titleTextView.text =
                            requireActivity().getString(R.string.onboarding_goal_weight_title_2)
                        minusButton.isVisible = true
                        plusButton.isVisible = true
                    }

                    else -> {
                        titleTextView.text =
                            requireActivity().getString(R.string.onboarding_current_weight_title)
                        minusButton.isVisible = false
                        plusButton.isVisible = false
                    }
                }
            } ?: run {
                titleTextView.text =
                    requireActivity().getString(R.string.onboarding_current_weight_title)
                minusButton.isVisible = false
                plusButton.isVisible = false
            }

            continueButton.setOnClickListener {
                viewModel.weight.value?.let {
                    delegate?.didSelectWeight(it)
                }
            }

            weightTextField.setHintTextColor(
                ContextCompat.getColor(
                    requireActivity(),
                    R.color.blue_base
                )
            )

            weightTextField.setOnEditorActionListener { v, actionId, event ->
                when (actionId) {
                    EditorInfo.IME_ACTION_DONE -> {
                        viewModel.weight.value?.let {
                            delegate?.didSelectWeight(it)
                        }
                        return@setOnEditorActionListener true
                    }

                    else -> false
                }
            }

            val inputFilter = object : InputFilter {
                override fun filter(
                    source: CharSequence?,
                    start: Int,
                    end: Int,
                    dest: Spanned?,
                    dstart: Int,
                    dend: Int
                ): CharSequence? {
                    val newText =
                        dest.toString().substring(0, dstart) + source.toString() + dest.toString()
                            .substring(dend)
                    if (newText.isFloat && newText.toFloat() < AppConstants.MAX_WEIGHT) {
                        newText.countDecimalPlaces()?.let {
                            return if (it > AppConstants.WEIGHT_DECIMAL_DIGITS) "" else null
                        } ?: run {
                            return ""
                        }
                    } else {
                        return ""
                    }
                }
            }

            weightTextField.filters = arrayOf(inputFilter)

            weightTextField.addTextChangedListener { editable ->
                editable?.let {
                    val newText = it.toString()
                    val floatValue = newText.toFloatOrNull()

                    if (newText.isEmpty()) {
                        viewModel.setWeight(floatValue)
                    } else if (floatValue != null && floatValue > 0) {
                        viewModel.setWeight(floatValue)
                    } else {
                        weightTextField.text = null
                    }
                } ?: run {
                    viewModel.setWeight(null)
                }
            }

            minusButton.setOnClickListener {
                addWeight(-1f)
            }

            plusButton.setOnClickListener {
                addWeight(1f)
            }
        }
    }

    fun setHint(currentWeight: Float) {
        hintWeight = currentWeight
        viewBinding.weightTextField.hint = currentWeight.toStringWithoutTrailingZero()
    }

    private fun setupBinding() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.weight.collect { weight ->
                val isEnabled = weight?.let {
                    it in AppConstants.MIN_WEIGHT..AppConstants.MAX_WEIGHT
                } ?: run {
                    false
                }
                viewBinding.continueButton.isEnabled = isEnabled
                viewBinding.continueButton.alpha = if (isEnabled) 1f else 0.3f
            }
        }
    }

    private fun addWeight(value: Float) {
        val currentWeight = viewModel.weight.value ?: hintWeight ?: 0f
        val newWeight = (currentWeight + value).roundTo(1)
        if (newWeight >= AppConstants.MIN_WEIGHT && newWeight <= AppConstants.MAX_WEIGHT) {
            viewBinding.apply {
                val cursorStartFromEnd =
                    weightTextField.text.length - weightTextField.selectionStart
                weightTextField.setText(newWeight.toStringWithoutTrailingZero())
                if (cursorStartFromEnd >= 0 && cursorStartFromEnd <= weightTextField.text.length) {
                    weightTextField.setSelection(weightTextField.text.length - cursorStartFromEnd)
                }
            }
        }
    }

    fun resetData() {
        viewBinding.weightTextField.text = null
    }

    override fun onResume() {
        super.onResume()
        viewBinding.weightTextField.focus(requireActivity())
    }

    override fun onPause() {
        super.onPause()
        viewBinding.weightTextField.unFocus(requireActivity())
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