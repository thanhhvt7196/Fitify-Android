package com.example.learnandroid.presentation.screens.onboarding.badHabit

import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.learnandroid.databinding.FragmentOnboardingBadHabitBinding
import com.example.learnandroid.domain.models.BadHabit
import com.example.learnandroid.presentation.screens.base.BaseViewBindingFragment
import kotlinx.coroutines.launch

class OnboardingBadHabitFragment :
    BaseViewBindingFragment<FragmentOnboardingBadHabitBinding, OnboardingBadHabitViewModel>(
        FragmentOnboardingBadHabitBinding::inflate
    ) {
    override val viewModel: OnboardingBadHabitViewModel by viewModels()

    private var adapter = BadHabitAdapter(emptyList())

    interface OnboardingBadHabitDelegate {
        fun didSelectBadHabits(badHabits: List<BadHabit>)
    }

    private var delegate: OnboardingBadHabitDelegate? = null

    companion object {
        fun newInstance(): OnboardingBadHabitFragment {
            return OnboardingBadHabitFragment()
        }
    }

    fun setAction(delegate: OnboardingBadHabitDelegate) {
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

            continueButton.setOnClickListener {
                delegate?.didSelectBadHabits(viewModel.allBadHabits.value.filter { it.second }
                    .map { it.first })
            }
        }
    }

    private fun setupRecyclerView() {
        viewBinding.apply {
            listView.layoutManager =
                LinearLayoutManager(requireActivity(), LinearLayoutManager.VERTICAL, false)
            val delegate = object : BadHabitAdapter.BadHabitAdapterDelegate {
                override fun didSelectBadHabit(badHabit: BadHabit) {
                    viewModel.selectHabit(badHabit)
                }
            }
            adapter.setAction(delegate)
            listView.adapter = adapter
        }
    }

    private fun setupBinding() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.allBadHabits.collect {
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