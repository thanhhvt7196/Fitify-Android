package com.example.learnandroid.presentation.screens.login

import android.util.Log
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.viewModelScope
import com.example.learnandroid.databinding.FragmentLoginBinding
import com.example.learnandroid.presentation.screens.base.BaseViewBindingFragment
import com.example.learnandroid.presentation.screens.onboarding.gender.OnboardingGenderFragment
import com.example.learnandroid.presentation.screens.onboarding.goal.OnboardingGoalFragment
import com.example.learnandroid.presentation.screens.onboarding.name.OnboardingNameFragment
import com.example.learnandroid.presentation.components.shared.backPressable.BackPressable
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.mapNotNull
import kotlinx.coroutines.launch

class LoginFragment : BaseViewBindingFragment<FragmentLoginBinding, LoginViewModel>(FragmentLoginBinding::inflate),
    BackPressable {

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
        val fragmentItems: Array<Fragment> = arrayOf(genderFragment, nameFragment, goalFragment)
        val viewpager = viewBinding.loginViewPager
        viewpager.isUserInputEnabled = false
        adapter = LoginPagerAdapter(this, fragmentItems)
        viewpager.adapter = adapter

        viewBinding.toolbar.setBackButtonOnClickListener {
            backToPreviousPage(viewModel.currentIndex.value)
        }
    }

    override suspend fun subscribeData() {
        viewModel.viewModelScope.launch {
            viewModel.currentIndex.collect { index ->
                viewBinding.toolbar.alpha = if (index == 0) 0f else 1f
                viewBinding.loginViewPager.currentItem = index
            }
        }

        viewModel.viewModelScope.launch {
            viewModel.gender.collect { gender ->
                gender?.let {
                    goToNextPage(viewModel.currentIndex.value)
                }
            }
        }

        viewModel.viewModelScope.launch {
            genderFragment.gender.collect { gender ->
                gender?.let {
                    viewModel.setGender(it)
                }
            }
        }
    }

    private fun backToPreviousPage(currentIndex: Int) {
        if (currentIndex >= 1) {
            viewModel.viewModelScope.launch {
                viewModel.setIndex(currentIndex - 1)
            }
        }
    }

    private fun goToNextPage(currentIndex: Int) {
        if (currentIndex < (viewBinding.loginViewPager.adapter?.itemCount ?: 0) - 1) {
            viewModel.viewModelScope.launch {
                viewModel.setIndex(currentIndex + 1)
            }
        }
    }

    override fun isBackPressEnabled(): Boolean {
        viewModel.currentIndex.value.let {
            if (it >= 1) {
                viewModel.setIndex(it - 1)
                return true
            } else {
                return false
            }
        }
    }
}