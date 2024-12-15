package com.example.learnandroid.presentation.screens.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding
import com.example.learnandroid.presentation.components.progressHUD.ProgressHUD

abstract class BaseViewBindingFragment<T : ViewBinding, VM : BaseViewModel>(private val initVb: (LayoutInflater, ViewGroup?, Boolean) -> T) :
    Fragment() {

    private var _viewBinding: T? = null
    val viewBinding: T get() = _viewBinding!!

    abstract val viewModel: VM

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _viewBinding = initVb.invoke(inflater, container, false)
        return viewBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setup()
    }

    open fun setup() {
        viewModel.isLoading.observe(viewLifecycleOwner) { isLoading ->
            context?.let {
                if (isLoading) ProgressHUD.show(it) else ProgressHUD.dismiss()
            }
        }
    }

    override fun onDestroyView() {
        _viewBinding = null
        super.onDestroyView()
    }
}