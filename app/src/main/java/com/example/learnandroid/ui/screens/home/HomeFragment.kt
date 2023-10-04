package com.example.learnandroid.ui.screens.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.learnandroid.databinding.FragmentHomeBinding
import com.example.learnandroid.ui.screens.base.BaseViewBindingFragment
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeFragment : BaseViewBindingFragment<FragmentHomeBinding, HomeViewModel>(FragmentHomeBinding::inflate) {
    override val viewModel: HomeViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (savedInstanceState == null) {
            viewModel.type = arguments?.getInt("key") ?: 0;
        } else {
            viewModel.type = savedInstanceState.getInt("key")
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.putInt("key", viewModel.type)
        super.onSaveInstanceState(outState)
    }

    override fun initView() {

    }

    override suspend fun subscribeData() {

    }
}