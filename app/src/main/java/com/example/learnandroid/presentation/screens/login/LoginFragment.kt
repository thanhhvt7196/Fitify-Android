package com.example.learnandroid.presentation.screens.login

import android.util.Log
import android.view.View
import android.view.View.OnClickListener
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.viewModelScope
import androidx.navigation.fragment.findNavController
import com.example.learnandroid.R
import com.example.learnandroid.databinding.FragmentLoginBinding
import com.example.learnandroid.presentation.components.shared.appToolBar.AppToolbar
import com.example.learnandroid.presentation.screens.base.BaseViewBindingFragment
import com.example.learnandroid.presentation.screens.onboarding.gender.OnboardingGenderFragment
import com.example.learnandroid.presentation.screens.onboarding.goal.OnboardingGoalFragment
import com.example.learnandroid.presentation.screens.onboarding.name.OnboardingNameFragment
import com.example.learnandroid.presentation.components.shared.backPressable.BackPressable
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.mapNotNull
import kotlinx.coroutines.launch

class LoginFragment : BaseViewBindingFragment<FragmentLoginBinding, LoginViewModel>(FragmentLoginBinding::inflate),
    BackPressable,OnClickListener {

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
        findNavController().popBackStack()
    }

    override fun initView() {
        genderFragment.setOnclick(this)
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
    }

    override suspend fun subscribeData() {
        viewLifecycleOwner.lifecycleScope.launch {
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

        genderFragment.loginType.collect { loginType ->
            findNavController().navigate(R.id.action_login_fragment_to_loginWithEmailFragment)
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

    override fun onClick(v: View?) {
        findNavController().navigate(R.id.action_login_fragment_to_loginWithEmailFragment)
    }
}