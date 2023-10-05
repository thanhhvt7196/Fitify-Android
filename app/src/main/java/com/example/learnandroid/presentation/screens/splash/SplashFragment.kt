package com.example.learnandroid.presentation.screens.splash

import android.os.Handler
import android.os.Looper
import androidx.navigation.fragment.findNavController
import com.example.learnandroid.R
import com.example.learnandroid.databinding.FragmentSplashBinding
import com.example.learnandroid.presentation.screens.base.BaseViewBindingFragment
import org.koin.androidx.viewmodel.ext.android.viewModel

class SplashFragment :
    BaseViewBindingFragment<FragmentSplashBinding, SplashViewModel>(FragmentSplashBinding::inflate) {
    override val viewModel: SplashViewModel by viewModel<SplashViewModel>()

    override fun initView() {
        Handler(Looper.getMainLooper()).postDelayed({findNavController().navigate(R.id.main_fragment)}, 1000)
    }

    override suspend fun subscribeData() {

    }
}