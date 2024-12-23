package com.example.fitifyandroid.presentation.screens.workouts

import androidx.fragment.app.viewModels
import com.example.fitifyandroid.databinding.FragmentWorkoutsBinding
import com.example.fitifyandroid.presentation.screens.base.BaseViewBindingFragment

class WorkoutsFragment : BaseViewBindingFragment<FragmentWorkoutsBinding, WorkoutsViewModel>(
    FragmentWorkoutsBinding::inflate
) {
    override val viewModel: WorkoutsViewModel by viewModels()

    companion object {
        fun newInstance(): WorkoutsFragment {
            return WorkoutsFragment()
        }
    }

    override fun setup() {
        super.setup()
        setupUI()
    }

    private fun setupUI() {

    }

}