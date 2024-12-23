package com.example.fitifyandroid.data.module

import com.example.fitifyandroid.presentation.screens.forgotPassword.ForgotPasswordViewModel
import com.example.fitifyandroid.presentation.screens.login.LoginViewModel
import com.example.fitifyandroid.presentation.screens.loginBottomSheet.LoginBottomSheetViewModel
import com.example.fitifyandroid.presentation.screens.loginWithEmail.LoginWithEmailViewModel
import com.example.fitifyandroid.presentation.screens.main.MainViewModel
import com.example.fitifyandroid.presentation.screens.profile.ProfileViewModel
import com.example.fitifyandroid.presentation.screens.plan.PlansViewModel
import com.example.fitifyandroid.presentation.screens.nutrition.NutritionViewModel
import com.example.fitifyandroid.presentation.screens.onboarding.activeStatus.OnboardingActiveStatusViewModel
import com.example.fitifyandroid.presentation.screens.onboarding.age.OnboardingAgeViewModel
import com.example.fitifyandroid.presentation.screens.onboarding.badHabit.OnboardingBadHabitViewModel
import com.example.fitifyandroid.presentation.screens.onboarding.commitContract.OnboardingCommitContractViewModel
import com.example.fitifyandroid.presentation.screens.onboarding.dailyWalk.OnboardingDailyWalkViewModel
import com.example.fitifyandroid.presentation.screens.onboarding.energyLevel.OnboardingEnergyLevelViewModel
import com.example.fitifyandroid.presentation.screens.onboarding.fitnessTool.OnboardingFitnessToolViewModel
import com.example.fitifyandroid.presentation.screens.onboarding.gender.OnboardingGenderViewModel
import com.example.fitifyandroid.presentation.screens.onboarding.goal.OnboardingGoalViewModel
import com.example.fitifyandroid.presentation.screens.onboarding.height.OnboardingHeightViewModel
import com.example.fitifyandroid.presentation.screens.onboarding.kneePain.OnboardingKneePainViewModel
import com.example.fitifyandroid.presentation.screens.onboarding.name.OnboardingNameViewModel
import com.example.fitifyandroid.presentation.screens.onboarding.planDay.OnboardingPlanDayViewModel
import com.example.fitifyandroid.presentation.screens.onboarding.googleFit.OnboardingGoogleFitViewModel
import com.example.fitifyandroid.presentation.screens.onboarding.planPace.OnboardingPlanPaceViewModel
import com.example.fitifyandroid.presentation.screens.onboarding.pushup.OnboardingPushUpViewModel
import com.example.fitifyandroid.presentation.screens.onboarding.salePitch.OnboardingSalePitchViewModel
import com.example.fitifyandroid.presentation.screens.onboarding.source.OnboardingSourceViewModel
import com.example.fitifyandroid.presentation.screens.onboarding.weight.OnboardingWeightViewModel
import com.example.fitifyandroid.presentation.screens.onboarding.workoutFrequency.OnboardingFrequencyViewModel
import com.example.fitifyandroid.presentation.screens.workouts.WorkoutsViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { OnboardingGoogleFitViewModel() }
    viewModel { OnboardingPlanDayViewModel() }
    viewModel { OnboardingCommitContractViewModel() }
    viewModel { OnboardingSourceViewModel() }
    viewModel { OnboardingPlanPaceViewModel() }
    viewModel { OnboardingEnergyLevelViewModel() }
    viewModel { OnboardingBadHabitViewModel() }
    viewModel { OnboardingDailyWalkViewModel() }
    viewModel { OnboardingPushUpViewModel() }
    viewModel { OnboardingFrequencyViewModel() }
    viewModel { OnboardingActiveStatusViewModel() }
    viewModel { OnboardingFitnessToolViewModel() }
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