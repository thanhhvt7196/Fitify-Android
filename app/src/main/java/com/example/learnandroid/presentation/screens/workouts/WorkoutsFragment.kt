package com.example.learnandroid.presentation.screens.workouts

import androidx.fragment.app.viewModels
import com.example.learnandroid.databinding.FragmentProfileBinding
import com.example.learnandroid.databinding.FragmentWorkoutsBinding
import com.example.learnandroid.presentation.screens.base.BaseViewBindingFragment

class WorkoutsFragment: BaseViewBindingFragment<FragmentWorkoutsBinding, WorkoutsViewModel>(
    FragmentWorkoutsBinding::inflate) {
    override val viewModel: WorkoutsViewModel by viewModels()

    override fun initView() {

    }

    override suspend fun subscribeData() {
    }

    companion object {
        fun newInstance(): WorkoutsFragment {
            return WorkoutsFragment()
        }
    }
}