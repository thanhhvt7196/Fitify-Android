package com.example.learnandroid.presentation.screens.onboarding.fitnessTool

import android.os.Bundle
import android.text.Html
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.widget.LinearLayout
import androidx.core.content.ContextCompat
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.learnandroid.R
import com.example.learnandroid.databinding.FragmentOnboardingFitnessToolBinding
import com.example.learnandroid.domain.models.FitnessTool
import com.example.learnandroid.presentation.screens.base.BaseViewBindingFragment
import com.example.learnandroid.presentation.screens.onboarding.age.OnboardingAgeFragment
import com.example.learnandroid.presentation.screens.onboarding.age.OnboardingAgeViewModel
import com.example.learnandroid.utils.constants.AppConstants
import com.example.learnandroid.utils.extensions.firstCapitalize
import com.example.learnandroid.utils.extensions.focus
import com.example.learnandroid.utils.extensions.unFocus
import kotlinx.coroutines.launch

class OnboardingFitnessToolFragment :
    BaseViewBindingFragment<FragmentOnboardingFitnessToolBinding, OnboardingFitneesToolViewModel>(
        FragmentOnboardingFitnessToolBinding::inflate
    ) {
    override val viewModel: OnboardingFitneesToolViewModel by viewModels()
    private var adapter = FitnessToolAdapter(emptyList())

    interface OnboardingFitnessToolDelegate {
        fun didSelectTools(tools: List<FitnessTool>)
    }

    private var delegate: OnboardingFitnessToolDelegate? = null

    companion object {
        const val tag = "OnboardingFitnessToolFragment"
        fun newInstance(): OnboardingFitnessToolFragment {
            return OnboardingFitnessToolFragment()
        }
    }

    fun setAction(delegate: OnboardingFitnessToolDelegate) {
        this.delegate = delegate
    }

    override fun setup() {
        super.setup()
        setupUI()
        setupBinding()
    }

    private fun setupUI() {
        viewBinding.apply {
            setupRecyclerView()

            val subtitle = requireActivity().getString(R.string.onboarding_tool_selection_subtitle)
            subtitleTextView.text = Html.fromHtml(subtitle, Html.FROM_HTML_MODE_LEGACY)

            continueButton.setOnClickListener {
                delegate?.didSelectTools(viewModel.allTools.value.filter { it.second }
                    .map { it.first })
            }
        }
    }

    private fun setupRecyclerView() {
        viewBinding.apply {
            listView.layoutManager =
                LinearLayoutManager(requireActivity(), LinearLayoutManager.VERTICAL, false)
            val delegate = object : FitnessToolAdapter.FitnessToolAdapterDelegate {
                override fun didSelectTool(tool: FitnessTool) {
                    viewModel.selectTool(tool)
                }
            }
            adapter.setAction(delegate)
            listView.adapter = adapter
        }
    }

    private fun setupBinding() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.allTools.collect {
                adapter.updateData(it)
            }
        }
    }

    fun resetData() {
        viewModel.resetData()
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