package com.example.fitifyandroid.presentation.screens.profile

import com.example.fitifyandroid.databinding.FragmentProfileBinding
import com.example.fitifyandroid.presentation.screens.base.BaseViewBindingFragment
import org.koin.androidx.viewmodel.ext.android.viewModel

class ProfileFragment :
    BaseViewBindingFragment<FragmentProfileBinding, ProfileViewModel>(FragmentProfileBinding::inflate) {

    override val viewModel: ProfileViewModel by viewModel()

    companion object {
        fun newInstance(): ProfileFragment {
            return ProfileFragment()
        }
    }

    override fun setup() {
        super.setup()
        setupUI()
    }

    private fun setupUI() {

    }
}