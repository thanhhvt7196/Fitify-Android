package com.example.learnandroid.data.module

import com.example.learnandroid.presentation.screens.profile.ProfileViewModel
import com.example.learnandroid.presentation.screens.plan.PlansViewModel
import com.example.learnandroid.presentation.screens.nutrition.NutritionViewModel
import com.example.learnandroid.presentation.screens.workouts.WorkoutsViewModel
import org.koin.dsl.module

val viewModelModule = module {
    single { PlansViewModel() }
    single { ProfileViewModel() }
    single { NutritionViewModel() }
    single { WorkoutsViewModel() }
}