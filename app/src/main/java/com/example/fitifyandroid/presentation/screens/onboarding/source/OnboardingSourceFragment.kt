package com.example.fitifyandroid.presentation.screens.onboarding.source

import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.fitifyandroid.databinding.FragmentOnboardingSourceBinding
import com.example.fitifyandroid.domain.models.Source
import com.example.fitifyandroid.presentation.screens.base.BaseViewBindingFragment
import kotlinx.coroutines.launch

class OnboardingSourceFragment :
    BaseViewBindingFragment<FragmentOnboardingSourceBinding, OnboardingSourceViewModel>(
        FragmentOnboardingSourceBinding::inflate
    ) {
    override val viewModel: OnboardingSourceViewModel by viewModels()

    private var adapter = SourceAdapter(emptyList())

    interface OnboardingSourceDelegate {
        fun didSelectSource(source: Source)
    }

    private var delegate: OnboardingSourceDelegate? = null

    companion object {
        fun newInstance(): OnboardingSourceFragment {
            return OnboardingSourceFragment()
        }
    }

    fun setAction(delegate: OnboardingSourceDelegate) {
        this.delegate = delegate
    }

    override fun setup() {
        super.setup()
        setupUI()
        setupBinding()
    }

    private fun setupUI() {
        setupRecyclerView()
    }

    private fun setupRecyclerView() {
        viewBinding.apply {
            listView.layoutManager =
                LinearLayoutManager(requireActivity(), LinearLayoutManager.VERTICAL, false)
            val delegate = object : SourceAdapter.SourceAdapterDelegate {
                override fun didSelectSource(source: Source) {
                    viewModel.selectSource(source)
                    delegate?.didSelectSource(source)
                }
            }
            adapter.setAction(delegate)
            listView.adapter = adapter
        }
    }

    private fun setupBinding() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.allSources.collect {
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