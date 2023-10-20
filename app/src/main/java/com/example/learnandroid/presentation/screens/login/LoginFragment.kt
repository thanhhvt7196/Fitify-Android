package com.example.learnandroid.presentation.screens.login

import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.example.learnandroid.databinding.FragmentLoginBinding
import com.example.learnandroid.presentation.screens.base.BaseViewBindingFragment
import com.example.learnandroid.presentation.screens.onboarding.gender.OnboardingGenderFragment
import com.example.learnandroid.presentation.screens.onboarding.goal.OnboardingGoalFragment
import com.example.learnandroid.presentation.screens.onboarding.name.OnboardingNameFragment
import com.example.learnandroid.utils.customviews.BackPressable

class LoginFragment : BaseViewBindingFragment<FragmentLoginBinding, LoginViewModel>(FragmentLoginBinding::inflate), BackPressable {

    override val viewModel: LoginViewModel by viewModels()
    private lateinit var adapter: LoginPagerAdapter

    private val genderFragment = OnboardingGenderFragment.newInstance()
    private val nameFragment = OnboardingNameFragment.newInstance()
    private val goalFragment = OnboardingGoalFragment.newInstance()

    companion object {
        const val tag = "LoginFragment"
        fun newInstance(): LoginFragment {
            return LoginFragment()
        }
    }

    override fun initView() {
        val fragmentItems = arrayOf(genderFragment, nameFragment, goalFragment) as Array<Fragment>
        val viewpager = viewBinding.loginViewPager
        viewpager.isUserInputEnabled = false
        adapter = LoginPagerAdapter(this, fragmentItems)
        viewpager.adapter = adapter

        viewBinding.toolbar.setBackButtonOnClickListener {
            viewModel.currentIndex.value?.let {
                backToPreviousPage()
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
            if (it >= 1) {
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