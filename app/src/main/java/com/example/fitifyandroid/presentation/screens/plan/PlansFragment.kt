package com.example.fitifyandroid.presentation.screens.plan

import com.example.fitifyandroid.databinding.FragmentPlansBinding
import com.example.fitifyandroid.presentation.screens.base.BaseViewBindingFragment
import org.koin.androidx.viewmodel.ext.android.viewModel

class PlansFragment :
    BaseViewBindingFragment<FragmentPlansBinding, PlansViewModel>(FragmentPlansBinding::inflate) {
    override val viewModel: PlansViewModel by viewModel()

    companion object {
        fun newInstance(): PlansFragment {
            return PlansFragment()
        }
    }

    override fun setup() {
        super.setup()
        setupUI()
    }

    private fun setupUI() {

    }
}