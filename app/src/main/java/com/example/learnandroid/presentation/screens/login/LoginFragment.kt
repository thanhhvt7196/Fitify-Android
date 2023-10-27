package com.example.learnandroid.presentation.screens.login

import android.os.Bundle
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.learnandroid.R
import com.example.learnandroid.databinding.FragmentLoginBinding
import com.example.learnandroid.domain.models.Gender
import com.example.learnandroid.domain.models.OnboardingGoal
import com.example.learnandroid.presentation.components.appToolBar.AppToolbar
import com.example.learnandroid.presentation.screens.base.BaseViewBindingFragment
import com.example.learnandroid.presentation.screens.onboarding.gender.OnboardingGenderFragment
import com.example.learnandroid.presentation.screens.onboarding.goal.OnboardingGoalFragment
import com.example.learnandroid.presentation.screens.onboarding.name.OnboardingNameFragment
import com.example.learnandroid.presentation.screens.loginBottomSheet.LoginType
import com.example.learnandroid.presentation.screens.onboarding.age.OnboardingAgeFragment
import com.example.learnandroid.presentation.screens.onboarding.salePitch.OnboardingSalePitchDelegate
import com.example.learnandroid.presentation.screens.onboarding.salePitch.OnboardingSalePitchFragment
import com.example.learnandroid.presentation.screens.onboarding.weight.OnboardingWeightFragment
import com.example.learnandroid.utils.extensions.play
import kotlinx.coroutines.launch

class LoginFragment :
    BaseViewBindingFragment<FragmentLoginBinding, LoginViewModel>(FragmentLoginBinding::inflate) {
    override val viewModel: LoginViewModel by viewModels()
    private lateinit var adapter: LoginPagerAdapter

    private val genderFragment = OnboardingGenderFragment.newInstance()
    private val nameFragment = OnboardingNameFragment.newInstance()
    private val goalFragment = OnboardingGoalFragment.newInstance()
    private val salePitchFragment = OnboardingSalePitchFragment.newInstance()
    private val ageFragment = OnboardingAgeFragment.newInstance()
    private val currentWeightFragment =
        OnboardingWeightFragment.currentWeightNewInstance()
    private val targetWeightFragment =
        OnboardingWeightFragment.targetWeightNewInstance()

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
        super.setup()
        setupLottie()
        setupViewPager()
        setupGenderView()
        setupNameView()
        setupGoalView()
        setupSalePitchView()
        setupAgeView()
        setupWeightView(currentWeightFragment)
        setupWeightView(targetWeightFragment)
    }

    private fun setupLottie() {
        viewBinding.progressLottieView.apply {
            setAnimation("onboarding-loader-blue.json")
            repeatCount = 0
            speed = 1f
        }
    }

    private fun setupViewPager() {
        val fragmentItems: Array<Fragment> =
            arrayOf(
                genderFragment,
                nameFragment,
                goalFragment,
                salePitchFragment,
                ageFragment,
                currentWeightFragment,
                targetWeightFragment
            )
        val viewpager = viewBinding.loginViewPager
        viewpager.isUserInputEnabled = false
        adapter = LoginPagerAdapter(this, fragmentItems)
        viewpager.adapter = adapter
        viewpager.offscreenPageLimit = fragmentItems.size

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
                setAnimationProgress(index)
            }
        }
    }

    private fun setupWeightView(fragment: OnboardingWeightFragment) {
        val args = Bundle()
        when (fragment) {
            currentWeightFragment -> args.putString("type", OnboardingWeightFragment.Type.CURRENT_WEIGHT.name)
            targetWeightFragment -> args.putString("type", OnboardingWeightFragment.Type.TARGET_WEIGHT.name)
        }
        fragment.arguments = args
        val delegate = object : OnboardingWeightFragment.OnboardingWeightDelegate {
            override fun didSelectWeight(weight: Float) {
                viewModel.setWeight(weight)
                if (fragment == currentWeightFragment) {
                    targetWeightFragment.setHint(weight)
                }
                goToNextPage()
            }
        }
        fragment.setAction(delegate)
    }

    private fun setupAgeView() {
        val delegate = object : OnboardingAgeFragment.OnboardingAgeDelegate {
            override fun didSelectAge(age: Int) {
                viewModel.setAge(age)
                goToNextPage()
            }
        }

        ageFragment.setAction(delegate)
    }

    private fun setupSalePitchView() {
        val delegate = object : OnboardingSalePitchDelegate {
            override fun didContinueTapped() {
                goToNextPage()
            }
        }
        salePitchFragment.setAction(delegate)
    }

    private fun setupGoalView() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.goal.collect { goal ->
                goal?.let {
                    goToNextPage()
                }
            }
        }

        val delegate = object : OnboardingGoalFragment.OnboardingGoalDelegate {
            override fun didSelectGoal(goal: OnboardingGoal) {
                viewLifecycleOwner.lifecycleScope.launch {
                    viewModel.setGoal(goal)
                }
                salePitchFragment.setGoal(goal)
            }
        }
        goalFragment.setAction(delegate)
    }

    private fun setupNameView() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.name.collect { name ->
                name?.let {
                    goToNextPage()
                }
            }
        }

        val onboardingNameDelegate = object : OnboardingNameFragment.OnboardingNameDelegate {
            override fun didSelectName(name: String) {
                viewLifecycleOwner.lifecycleScope.launch {
                    viewModel.setName(name)
                }
            }
        }
        nameFragment.setAction(onboardingNameDelegate)
    }

    private fun setupGenderView() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.gender.collect { gender ->
                gender?.let {
                    goToNextPage()
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
                resetData()

                goalFragment.setGender(gender)
                salePitchFragment.setGender(gender)
            }
        }

        genderFragment.setAction(onboardingGenderDelegate)
    }

    private fun resetData() {
        if (nameFragment.isAdded) {
            nameFragment.resetData()
        }
        if (goalFragment.isAdded) {
            goalFragment.resetData()
        }

        if (ageFragment.isAdded) {
            ageFragment.resetData()
        }

        if (currentWeightFragment.isAdded) {
            currentWeightFragment.resetData()
        }

        if (targetWeightFragment.isAdded) {
            targetWeightFragment.resetData()
        }
    }

    private fun backToPreviousPage(currentIndex: Int) {
        if (currentIndex >= 1) {
            viewLifecycleOwner.lifecycleScope.launch {
                viewModel.setIndex(currentIndex - 1)
            }
        }
    }

    private fun goToNextPage() {
        val currentIndex = viewModel.currentIndex.value
        if (currentIndex < (viewBinding.loginViewPager.adapter?.itemCount ?: 0) - 1) {
            viewLifecycleOwner.lifecycleScope.launch {
                viewModel.setIndex(currentIndex + 1)
            }
        }
    }

    private fun setAnimationProgress(currentIndex: Int) {
        viewBinding.loginViewPager.adapter?.let {
            if (currentIndex > 0 && it.itemCount > 1) {
                val targetProgress = currentIndex.toFloat() / (it.itemCount.toFloat() - 1)
                viewBinding.progressLottieView.play(
                    viewBinding.progressLottieView.progress,
                    targetProgress
                )
            } else {
                viewBinding.progressLottieView.play(viewBinding.progressLottieView.progress, 0f)
            }
        }
    }
}