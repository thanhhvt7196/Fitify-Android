package com.example.learnandroid.data.module

import com.example.learnandroid.presentation.screens.forgotPassword.ForgotPasswordViewModel
import com.example.learnandroid.presentation.screens.login.LoginViewModel
import com.example.learnandroid.presentation.screens.loginBottomSheet.LoginBottomSheetViewModel
import com.example.learnandroid.presentation.screens.loginWithEmail.LoginWithEmailViewModel
import com.example.learnandroid.presentation.screens.main.MainViewModel
import com.example.learnandroid.presentation.screens.profile.ProfileViewModel
import com.example.learnandroid.presentation.screens.plan.PlansViewModel
import com.example.learnandroid.presentation.screens.nutrition.NutritionViewModel
import com.example.learnandroid.presentation.screens.onboarding.activeStatus.OnboardingActiveStatusViewModel
import com.example.learnandroid.presentation.screens.onboarding.age.OnboardingAgeViewModel
import com.example.learnandroid.presentation.screens.onboarding.badHabit.OnboardingBadHabitViewModel
import com.example.learnandroid.presentation.screens.onboarding.commitContract.OnboardingCommitContractViewModel
import com.example.learnandroid.presentation.screens.onboarding.dailyWalk.OnboardingDailyWalkViewModel
import com.example.learnandroid.presentation.screens.onboarding.energyLevel.OnboardingEnergyLevelViewModel
import com.example.learnandroid.presentation.screens.onboarding.fitnessTool.OnboardingFitneesToolViewModel
import com.example.learnandroid.presentation.screens.onboarding.gender.OnboardingGenderViewModel
import com.example.learnandroid.presentation.screens.onboarding.goal.OnboardingGoalViewModel
import com.example.learnandroid.presentation.screens.onboarding.height.OnboardingHeightViewModel
import com.example.learnandroid.presentation.screens.onboarding.kneePain.OnboardingKneePainViewModel
import com.example.learnandroid.presentation.screens.onboarding.name.OnboardingNameViewModel
import com.example.learnandroid.presentation.screens.onboarding.planPace.OnboardingPlanPaceViewModel
import com.example.learnandroid.presentation.screens.onboarding.pushup.OnboardingPushUpViewModel
import com.example.learnandroid.presentation.screens.onboarding.salePitch.OnboardingSalePitchViewModel
import com.example.learnandroid.presentation.screens.onboarding.source.OnboardingSourceViewModel
import com.example.learnandroid.presentation.screens.onboarding.weight.OnboardingWeightViewModel
import com.example.learnandroid.presentation.screens.onboarding.workoutFrequency.OnboardingFrequencyViewModel
import com.example.learnandroid.presentation.screens.workouts.WorkoutsViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { OnboardingCommitContractViewModel() }
    viewModel { OnboardingSourceViewModel() }
    viewModel { OnboardingPlanPaceViewModel() }
    viewModel { OnboardingEnergyLevelViewModel() }
    viewModel { OnboardingBadHabitViewModel() }
    viewModel { OnboardingDailyWalkViewModel() }
    viewModel { OnboardingPushUpViewModel() }
    viewModel { OnboardingFrequencyViewModel() }
    viewModel { OnboardingActiveStatusViewModel() }
    viewModel { OnboardingFitneesToolViewModel() }
    viewModel { OnboardingKneePainViewModel() }
    viewModel { OnboardingHeightViewModel() }
    viewModel { OnboardingWeightViewModel() }
    viewModel { OnboardingAgeViewModel() }
    viewModel { OnboardingSalePitchViewModel() }
    viewModel { ForgotPasswordViewModel() }
    viewModel { LoginWithEmailViewModel() }
    viewModel { MainViewModel() }
    viewModel { LoginViewModel() }
    viewModel { LoginBottomSheetViewModel() }
    viewModel { OnboardingGenderViewModel() }
    viewModel { OnboardingGoalViewModel() }
    viewModel { OnboardingNameViewModel() }
    viewModel { PlansViewModel() }
    viewModel { ProfileViewModel() }
    viewModel { NutritionViewModel() }
    viewModel { WorkoutsViewModel() }
}