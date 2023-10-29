package com.example.learnandroid.presentation.screens.login

import android.os.Bundle
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.learnandroid.R
import com.example.learnandroid.databinding.FragmentLoginBinding
import com.example.learnandroid.domain.models.ActiveStatus
import com.example.learnandroid.domain.models.BadHabit
import com.example.learnandroid.domain.models.DailyWalk
import com.example.learnandroid.domain.models.EnergyLevel
import com.example.learnandroid.domain.models.FitnessTool
import com.example.learnandroid.domain.models.Gender
import com.example.learnandroid.domain.models.KneePain
import com.example.learnandroid.domain.models.OnboardingGoal
import com.example.learnandroid.domain.models.PlanPace
import com.example.learnandroid.domain.models.PushUp
import com.example.learnandroid.domain.models.Source
import com.example.learnandroid.domain.models.WorkoutFrequency
import com.example.learnandroid.presentation.components.appToolBar.AppToolbar
import com.example.learnandroid.presentation.screens.base.BaseViewBindingFragment
import com.example.learnandroid.presentation.screens.onboarding.gender.OnboardingGenderFragment
import com.example.learnandroid.presentation.screens.onboarding.goal.OnboardingGoalFragment
import com.example.learnandroid.presentation.screens.onboarding.name.OnboardingNameFragment
import com.example.learnandroid.presentation.screens.loginBottomSheet.LoginType
import com.example.learnandroid.presentation.screens.onboarding.activeStatus.OnboardingActiveStatusFragment
import com.example.learnandroid.presentation.screens.onboarding.age.OnboardingAgeFragment
import com.example.learnandroid.presentation.screens.onboarding.badHabit.OnboardingBadHabitFragment
import com.example.learnandroid.presentation.screens.onboarding.commitContract.OnboardingCommitContractFragment
import com.example.learnandroid.presentation.screens.onboarding.dailyWalk.OnboardingDailyWalkFragment
import com.example.learnandroid.presentation.screens.onboarding.energyLevel.OnboardingEnergyLevelFragment
import com.example.learnandroid.presentation.screens.onboarding.fitnessTool.OnboardingFitnessToolFragment
import com.example.learnandroid.presentation.screens.onboarding.height.OnboardingHeightFragment
import com.example.learnandroid.presentation.screens.onboarding.kneePain.OnboardingKneePainFragment
import com.example.learnandroid.presentation.screens.onboarding.planPace.OnboardingPlanPaceFragment
import com.example.learnandroid.presentation.screens.onboarding.pushup.OnboardingPushUpFragment
import com.example.learnandroid.presentation.screens.onboarding.salePitch.OnboardingSalePitchFragment
import com.example.learnandroid.presentation.screens.onboarding.source.OnboardingSourceFragment
import com.example.learnandroid.presentation.screens.onboarding.weight.OnboardingWeightFragment
import com.example.learnandroid.presentation.screens.onboarding.workoutFrequency.OnboardingFrequencyFragment
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
    private val heightFragment = OnboardingHeightFragment.newInstance()
    private val currentWeightFragment =
        OnboardingWeightFragment.currentWeightNewInstance()
    private val targetWeightFragment =
        OnboardingWeightFragment.targetWeightNewInstance()
    private val kneePainFragment = OnboardingKneePainFragment.newInstance()
    private val fitnessToolFragment = OnboardingFitnessToolFragment.newInstance()
    private val activeStatusFragment = OnboardingActiveStatusFragment.newInstance()
    private val frequencyFragment = OnboardingFrequencyFragment.newInstance()
    private val pushUpFragment = OnboardingPushUpFragment.newInstance()
    private val dailyWalkFragment = OnboardingDailyWalkFragment.newInstance()
    private val badHabitFragment = OnboardingBadHabitFragment.newInstance()
    private val energyLevelFragment = OnboardingEnergyLevelFragment.newInstance()
    private val planPaceFragment = OnboardingPlanPaceFragment.newInstance()
    private val sourceFragment = OnboardingSourceFragment.newInstance()
    private val commitContractFragment = OnboardingCommitContractFragment.newInstance()

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
        setupHeightView()
        setupWeightView(currentWeightFragment)
        setupWeightView(targetWeightFragment)
        setupKneePainView()
        setupFitnessToolView()
        setupActiveStatusView()
        setupFrequencyView()
        setupPushUpView()
        setupDailyWalkView()
        setupBadHabitView()
        setupEnergyLevelView()
        setupPlanPaceView()
        setupSourceView()
        setupCommitContractView()
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
                commitContractFragment,
                nameFragment,
                goalFragment,
                salePitchFragment,
                ageFragment,
                heightFragment,
                currentWeightFragment,
                targetWeightFragment,
                kneePainFragment,
                fitnessToolFragment,
                activeStatusFragment,
                frequencyFragment,
                sourceFragment,
                pushUpFragment,
                dailyWalkFragment,
                badHabitFragment,
                energyLevelFragment,
                planPaceFragment
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

    private fun setupCommitContractView() {
        val delegate = object : OnboardingCommitContractFragment.OnboardingCommitContractDelegate {
            override fun didCommitted() {
                goToNextPage()
            }
        }
        commitContractFragment.setAction(delegate)
    }

    private fun setupSourceView() {
        val delegate = object : OnboardingSourceFragment.OnboardingSourceDelegate {
            override fun didSelectSource(source: Source) {
                viewModel.setSource(source)
                goToNextPage()
            }
        }
        sourceFragment.setAction(delegate)
    }

    private fun setupPlanPaceView() {
        val delegate = object : OnboardingPlanPaceFragment.OnboardingPlanPaceDelegate {
            override fun didSelectPlanPace(planPace: PlanPace) {
                viewModel.setPlanPace(planPace)
                goToNextPage()
            }
        }
        planPaceFragment.setAction(delegate)
    }

    private fun setupEnergyLevelView() {
        val delegate = object : OnboardingEnergyLevelFragment.OnboardingEnergyLevelDelegate {
            override fun didSelectEnergyLevel(energyLevel: EnergyLevel) {
                viewModel.setEnergyLevel(energyLevel)
                goToNextPage()
            }
        }
        energyLevelFragment.setAction(delegate)
    }

    private fun setupBadHabitView() {
        val delegate = object : OnboardingBadHabitFragment.OnboardingBadHabitDelegate {
            override fun didSelectBadHabits(badHabits: List<BadHabit>) {
                viewModel.setBadHabits(badHabits)
                goToNextPage()
            }
        }
        badHabitFragment.setAction(delegate)
    }

    private fun setupDailyWalkView() {
        val delegate = object : OnboardingDailyWalkFragment.OnboardingDailyWalkDelegate {
            override fun didSelectDailyWalk(dailyWalk: DailyWalk) {
                viewModel.setDailyWalk(dailyWalk)
                goToNextPage()
            }
        }
        dailyWalkFragment.setAction(delegate)
    }

    private fun setupPushUpView() {
        val delegate = object : OnboardingPushUpFragment.OnboardingPushUpDelegate {
            override fun didSelectPushUp(pushUp: PushUp) {
                viewModel.setPushUp(pushUp)
                goToNextPage()
            }
        }
        pushUpFragment.setAction(delegate)
    }

    private fun setupActiveStatusView() {
        val delegate = object : OnboardingActiveStatusFragment.OnboardingActiveStatusDelegate {
            override fun didSelectActiveStatus(status: ActiveStatus) {
                viewModel.setActiveStatus(status)
                goToNextPage()
            }
        }
        activeStatusFragment.setAction(delegate)
    }

    private fun setupFrequencyView() {
        val delegate = object : OnboardingFrequencyFragment.OnboardingFrequencyDelegate {
            override fun didSelectFrequency(frequency: WorkoutFrequency) {
                viewModel.setFrequency(frequency)
                goToNextPage()
            }
        }
        frequencyFragment.setAction(delegate)
    }

    private fun setupFitnessToolView() {
        val delegate = object : OnboardingFitnessToolFragment.OnboardingFitnessToolDelegate {
            override fun didSelectTools(tools: List<FitnessTool>) {
                viewModel.setFitnessTools(tools)
                goToNextPage()
            }
        }
        fitnessToolFragment.setAction(delegate)
    }

    private fun setupKneePainView() {
        val delegate = object : OnboardingKneePainFragment.OnboardingKneePainDelegate {
            override fun didSelectKneePain(kneePain: KneePain) {
                viewModel.setKneePain(kneePain)
                goToNextPage()
            }
        }
        kneePainFragment.setAction(delegate)
    }

    private fun setupHeightView() {
        val delegate = object : OnboardingHeightFragment.OnboardingHeightDelegate {
            override fun didSelectHeight(height: Int) {
                viewModel.setHeight(height)
                goToNextPage()
            }
        }
        heightFragment.setAction(delegate)
    }

    private fun setupWeightView(fragment: OnboardingWeightFragment) {
        val args = Bundle()
        when (fragment) {
            currentWeightFragment -> args.putString(
                "type",
                OnboardingWeightFragment.Type.CURRENT_WEIGHT.name
            )

            targetWeightFragment -> args.putString(
                "type",
                OnboardingWeightFragment.Type.TARGET_WEIGHT.name
            )
        }
        fragment.arguments = args
        val delegate = object : OnboardingWeightFragment.OnboardingWeightDelegate {
            override fun didSelectWeight(weight: Float) {
                when (fragment) {
                    currentWeightFragment -> {
                        viewModel.setWeight(weight)
                        targetWeightFragment.setHint(weight)
                    }

                    targetWeightFragment -> {
                        viewModel.setTargetWeight(weight)
                    }
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
        val delegate = object : OnboardingSalePitchFragment.OnboardingSalePitchDelegate {
            override fun didContinueTapped() {
                goToNextPage()
            }
        }
        salePitchFragment.setAction(delegate)
    }

    private fun setupGoalView() {
        val delegate = object : OnboardingGoalFragment.OnboardingGoalDelegate {
            override fun didSelectGoal(goal: OnboardingGoal) {
                viewLifecycleOwner.lifecycleScope.launch {
                    viewModel.setGoal(goal)
                    goToNextPage()
                }
                salePitchFragment.setGoal(goal)
            }
        }
        goalFragment.setAction(delegate)
    }

    private fun setupNameView() {
        val onboardingNameDelegate = object : OnboardingNameFragment.OnboardingNameDelegate {
            override fun didSelectName(name: String) {
                viewLifecycleOwner.lifecycleScope.launch {
                    viewModel.setName(name)
                    goToNextPage()
                }
            }
        }
        nameFragment.setAction(onboardingNameDelegate)
    }

    private fun setupGenderView() {
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
                goToNextPage()
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

        if (heightFragment.isAdded) {
            heightFragment.resetData()
        }

        if (currentWeightFragment.isAdded) {
            currentWeightFragment.resetData()
        }

        if (targetWeightFragment.isAdded) {
            targetWeightFragment.resetData()
        }

        if (kneePainFragment.isAdded) {
            kneePainFragment.resetData()
        }

        if (fitnessToolFragment.isAdded) {
            fitnessToolFragment.resetData()
        }

        if (activeStatusFragment.isAdded) {
            activeStatusFragment.resetData()
        }

        if (frequencyFragment.isAdded) {
            frequencyFragment.resetData()
        }

        if (pushUpFragment.isAdded) {
            pushUpFragment.resetData()
        }

        if (dailyWalkFragment.isAdded) {
            dailyWalkFragment.resetData()
        }

        if (badHabitFragment.isAdded) {
            badHabitFragment.resetData()
        }

        if (energyLevelFragment.isAdded) {
            energyLevelFragment.resetData()
        }

        if (planPaceFragment.isAdded) {
            planPaceFragment.resetData()
        }

        if (sourceFragment.isAdded) {
            sourceFragment.resetData()
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