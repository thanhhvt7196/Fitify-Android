package com.example.learnandroid.presentation.screens.login

import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.example.learnandroid.LoginActivity
import com.example.learnandroid.databinding.FragmentLoginBinding
import com.example.learnandroid.presentation.screens.base.BaseViewBindingFragment
import com.example.learnandroid.utils.customviews.BackPressable

class LoginFragment : BaseViewBindingFragment<FragmentLoginBinding, LoginViewModel>(FragmentLoginBinding::inflate), BackPressable {

    override val viewModel: LoginViewModel by viewModels()
    private lateinit var adapter: LoginPagerAdapter

    companion object {
        const val tag = "LoginFragment"
        fun newInstance(): LoginFragment {
            return LoginFragment()
        }
    }

    override fun initView() {
        val viewpager = viewBinding.loginViewPager
        viewpager.isUserInputEnabled = false
        adapter = LoginPagerAdapter(this)
        viewpager.adapter = adapter

        viewBinding.toolbar.setOnClickListener {
            viewModel.currentIndex.value?.let {
                if (it > 0) {
                    backToPreviousPage()
                }
            }
        }

        viewBinding.testButton.setOnClickListener {
            viewModel.currentIndex.value?.let {
                if (it < adapter.itemCount - 1) {
                    viewModel.setIndex(it + 1)
                }
            }
        }
    }

    override suspend fun subscribeData() {
        viewModel.currentIndex.observe(this, Observer { index ->
            index?.let {
                viewBinding.toolbar.alpha = if (it == 0) 0f else 1f
                viewBinding.loginViewPager.currentItem = it
            } ?: run {
                viewBinding.toolbar.alpha = 0f
            }
        })
    }

    private fun backToPreviousPage() {
        viewModel.currentIndex.value?.let {
            if (it > 0) {
                viewModel.setIndex(it - 1)
            }
        }
    }

    override fun isBackPressEnabled(): Boolean {
        viewModel.currentIndex.value?.let {
            if (it >= 1) {
                viewModel.setIndex(it - 1)
                return true
            } else {
                return false
            }
        } ?: run {
            return false
        }
    }
}