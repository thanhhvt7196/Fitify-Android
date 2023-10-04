package com.example.learnandroid.ui.screens.splash

import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.learnandroid.R
import com.example.learnandroid.databinding.FragmentSplashBinding
import com.example.learnandroid.ui.screens.base.BaseViewBindingFragment
import org.koin.androidx.viewmodel.ext.android.activityViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class SplashFragment :
    BaseViewBindingFragment<FragmentSplashBinding, SplashViewModel>(FragmentSplashBinding::inflate) {
    override val viewModel: SplashViewModel by viewModel<SplashViewModel>()

    override fun initView() {
        viewBinding.apply {
            splashButton.setOnClickListener {
                viewModel.setText("thanh dep trai")
//                val bundle = Bundle()
//                bundle.putInt("key", 1)
//                findNavController().navigate(R.id.dashboard_fragment, bundle)

            }
        }
    }

    override suspend fun subscribeData() {
        viewModel.text.observe(this) {
            viewBinding.testTextView.text = it
        }
    }

}