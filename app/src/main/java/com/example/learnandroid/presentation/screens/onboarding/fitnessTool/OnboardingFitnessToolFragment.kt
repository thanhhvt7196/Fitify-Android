package com.example.learnandroid.presentation.screens.onboarding.fitnessTool

import android.text.Html
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.learnandroid.R
import com.example.learnandroid.databinding.FragmentOnboardingFitnessToolBinding
import com.example.learnandroid.domain.models.FitnessTool
import com.example.learnandroid.presentation.screens.base.BaseViewBindingFragment
import kotlinx.coroutines.launch

class OnboardingFitnessToolFragment :
    BaseViewBindingFragment<FragmentOnboardingFitnessToolBinding, OnboardingFitnessToolViewModel>(
        FragmentOnboardingFitnessToolBinding::inflate
    ) {
    override val viewModel: OnboardingFitnessToolViewModel by viewModels()
    private var adapter = FitnessToolAdapter(emptyList())

    interface OnboardingFitnessToolDelegate {
        fun didSelectTools(tools: List<FitnessTool>)
    }

    private var delegate: OnboardingFitnessToolDelegate? = null

    companion object {
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