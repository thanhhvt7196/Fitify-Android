package com.example.learnandroid.presentation.screens.nutrition

import com.example.learnandroid.databinding.FragmentNutritionBinding
import com.example.learnandroid.presentation.screens.base.BaseViewBindingFragment
import org.koin.androidx.viewmodel.ext.android.viewModel

class NutritionFragment : BaseViewBindingFragment<FragmentNutritionBinding, NutritionViewModel>(FragmentNutritionBinding::inflate) {
    override val viewModel: NutritionViewModel by viewModel()

    companion object {
        fun newInstance(): NutritionFragment {
            return NutritionFragment()
        }
    }
    override fun initView() {
    }

    override suspend fun subscribeData() {
    }
}