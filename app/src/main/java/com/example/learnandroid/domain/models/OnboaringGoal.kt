package com.example.learnandroid.domain.models

import android.content.Context
import com.example.learnandroid.R

enum class OnboardingGoal : Iterable<OnboardingGoal> {
    GET_TONED {
        override fun getDisplayName(context: Context, gender: Gender): String {
            return when (gender) {
                Gender.FEMALE -> context.getString(R.string.onboarding_goal_get_toned)
                Gender.MALE -> context.getString(R.string.onboarding_goal_gain_muscle)
            }
        }

        override fun getImageResource(context: Context, gender: Gender): Int {
            return when (gender) {
                Gender.MALE -> R.drawable.onboarding_goal_build_muscle
                Gender.FEMALE -> R.drawable.onboarding_goal_get_toned
            }
        }
    },
    LOSE_WEIGHT {
        override fun getDisplayName(context: Context, gender: Gender): String =
            context.getString(R.string.onboarding_goal_lose_weight)

        override fun getImageResource(context: Context, gender: Gender): Int {
            return R.drawable.onboarding_goal_lose_weight
        }
    },
    GET_FITTER {
        override fun getDisplayName(context: Context, gender: Gender): String =
            context.getString(R.string.onboarding_goal_get_fitter)

        override fun getImageResource(context: Context, gender: Gender): Int {
            return when (gender) {
                Gender.MALE -> R.drawable.img_onb_getfitter_m
                Gender.FEMALE -> R.drawable.img_onb_getfitter_f
            }
        }
    };

    abstract fun getDisplayName(context: Context, gender: Gender): String
    abstract fun getImageResource(context: Context, gender: Gender): Int

    override fun iterator(): Iterator<OnboardingGoal> = enumValues<OnboardingGoal>().iterator()
}