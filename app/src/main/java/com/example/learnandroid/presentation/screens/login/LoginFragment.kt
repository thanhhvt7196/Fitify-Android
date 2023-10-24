package com.example.learnandroid.presentation.screens.login

import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.learnandroid.R
import com.example.learnandroid.databinding.FragmentLoginBinding
import com.example.learnandroid.domain.models.Gender
import com.example.learnandroid.presentation.components.appToolBar.AppToolbar
import com.example.learnandroid.presentation.screens.base.BaseViewBindingFragment
import com.example.learnandroid.presentation.screens.onboarding.gender.OnboardingGenderFragment
import com.example.learnandroid.presentation.screens.onboarding.goal.OnboardingGoalFragment
import com.example.learnandroid.presentation.screens.onboarding.name.OnboardingNameFragment
import com.example.learnandroid.presentation.screens.loginBottomSheet.LoginType
import kotlinx.coroutines.launch

class LoginFragment : BaseViewBindingFragment<FragmentLoginBinding, LoginViewModel>(FragmentLoginBinding::inflate) {

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
    private val onBackPressedCallback = object : OnBackPressedCallback(true) {
        override fun handleOnBackPressed() {
          this@LoginFragment.handleOnBackPressed()
        }
    }

    private fun handleOnBackPressed() {
        if (viewModel.currentIndex.value >= 1) {
            viewModel.setIndex(viewModel.currentIndex.value - 1)
        }
    }

    override fun setup() {
        val fragmentItems: Array<Fragment> = arrayOf(genderFragment, nameFragment, goalFragment)
        val viewpager = viewBinding.loginViewPager
        viewpager.isUserInputEnabled = false
        adapter = LoginPagerAdapter(this, fragmentItems)
        viewpager.adapter = adapter

        viewBinding.toolbar.apply {
            setBackButtonType(AppToolbar.BackButtonType.POP)
            setBackButtonOnClickListener {
                backToPreviousPage(viewModel.currentIndex.value)
            }
        }
        requireActivity().onBackPressedDispatcher.addCallback(this, onBackPressedCallback)

        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.currentIndex.collect { index ->
                viewBinding.toolbar.alpha = if (index == 0) 0f else 1f
                viewBinding.loginViewPager.currentItem = index
            }
        }

        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.gender.collect { gender ->
                gender?.let {
                    goToNextPage(viewModel.currentIndex.value)
                }
            }
        }

        val onboardingGenderDelegate = object : OnboardingGenderFragment.OnboardingGenderDelegate {
            override fun didSelectLoginType(loginType: LoginType) {
                findNavController().navigate(R.id.login_to_login_email)
            }

            override fun didSelectGender(gender: Gender) {
                viewLifecycleOwner.lifecycleScope.launch {
                    viewModel.setGender(gender)
                }
            }
        }

        genderFragment.setAction(onboardingGenderDelegate)
    }

    private fun backToPreviousPage(currentIndex: Int) {
        if (currentIndex >= 1) {
            viewLifecycleOwner.lifecycleScope.launch {
                viewModel.setIndex(currentIndex - 1)
            }
        }
    }

    private fun goToNextPage(currentIndex: Int) {
        if (currentIndex < (viewBinding.loginViewPager.adapter?.itemCount ?: 0) - 1) {
            viewLifecycleOwner.lifecycleScope.launch {
                viewModel.setIndex(currentIndex + 1)
            }
        }
    }
}