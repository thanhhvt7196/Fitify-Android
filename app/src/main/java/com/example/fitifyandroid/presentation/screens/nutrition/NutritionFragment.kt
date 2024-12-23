package com.example.fitifyandroid.presentation.screens.nutrition

import com.example.fitifyandroid.databinding.FragmentNutritionBinding
import com.example.fitifyandroid.presentation.screens.base.BaseViewBindingFragment
import org.koin.androidx.viewmodel.ext.android.viewModel

class NutritionFragment :
    BaseViewBindingFragment<FragmentNutritionBinding, NutritionViewModel>(FragmentNutritionBinding::inflate) {
    override val viewModel: NutritionViewModel by viewModel()

    companion object {
        fun newInstance(): NutritionFragment {
            return NutritionFragment()
        }
    }

    override fun setup() {
        super.setup()
        setupUI()
    }

    private fun setupUI() {

    }
}