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

        override fun getSalePitchTitle(context: Context, gender: Gender): String {
            return when (gender) {
                Gender.FEMALE -> context.getString(R.string.pitch_onboarding_goal_female_get_toned_title)
                Gender.MALE -> context.getString(R.string.pitch_onboarding_goal_male_gain_muscle_title)
            }
        }

        override fun getSalePitchMessage(context: Context, gender: Gender): String {
            return context.getString(R.string.pitch_onboarding_goal_v2_message)
        }

        override fun getSalePicthImageResource(context: Context, gender: Gender): Int {
            return when (gender) {
                Gender.MALE -> R.drawable.sale_pitch_male_gain_muscle
                Gender.FEMALE -> R.drawable.sale_pitch_female_get_toned
            }
        }
    },
    LOSE_WEIGHT {
        override fun getDisplayName(context: Context, gender: Gender): String =
            context.getString(R.string.onboarding_goal_lose_weight)

        override fun getImageResource(context: Context, gender: Gender): Int {
            return R.drawable.onboarding_goal_lose_weight
        }

        override fun getSalePitchTitle(context: Context, gender: Gender): String {
            return when (gender) {
                Gender.FEMALE -> context.getString(R.string.pitch_onboarding_goal_female_lose_weight_title)
                Gender.MALE -> context.getString(R.string.pitch_onboarding_goal_male_lose_weight_title)
            }
        }

        override fun getSalePitchMessage(context: Context, gender: Gender): String {
            return context.getString(R.string.pitch_onboarding_goal_v2_message)
        }

        override fun getSalePicthImageResource(context: Context, gender: Gender): Int {
            return when (gender) {
                Gender.MALE -> R.drawable.sale_pitch_male_lose_weight
                Gender.FEMALE -> R.drawable.sale_pitch_female_lose_weight
            }
        }
    };

    abstract fun getDisplayName(context: Context, gender: Gender): String
    abstract fun getSalePitchTitle(context: Context, gender: Gender): String
    abstract fun getSalePicthImageResource(context: Context, gender: Gender): Int
    abstract fun getSalePitchMessage(context: Context, gender: Gender): String
    abstract fun getImageResource(context: Context, gender: Gender): Int

    override fun iterator(): Iterator<OnboardingGoal> = enumValues<OnboardingGoal>().iterator()
}