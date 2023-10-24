package com.example.learnandroid.presentation.screens.plan

import com.example.learnandroid.databinding.FragmentPlansBinding
import com.example.learnandroid.presentation.screens.base.BaseViewBindingFragment
import org.koin.androidx.viewmodel.ext.android.viewModel

class PlansFragment : BaseViewBindingFragment<FragmentPlansBinding, PlansViewModel>(FragmentPlansBinding::inflate) {
    override val viewModel: PlansViewModel by viewModel()

    companion object {
        const val tag = "PlansFragment"
        fun newInstance(): PlansFragment {
            return PlansFragment()
        }
    }

    override fun setup() {
        super.setup()
    }
}