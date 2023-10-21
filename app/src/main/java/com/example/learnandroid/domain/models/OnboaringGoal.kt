package com.example.learnandroid.domain.models

import android.content.Context
import com.example.learnandroid.R

enum class OnboaringGoal : Iterable<OnboaringGoal> {
    GET_TONED {
        override fun getDisplayName(context: Context, gender: Gender): String {
            return when (gender) {
                Gender.FEMALE -> context.getString(R.string.onboarding_goal_get_toned)
                Gender.MALE -> context.getString(R.string.onboarding_goal_gain_muscle)
            }
        }

        override fun getImageResource(context: Context, gender: Gender): Int {
            return when (gender) {
                Gender.MALE -> R.drawable.img_onboarding_goal_male_gainmuscle
                Gender.FEMALE -> R.drawable.img_onboarding_goal_female_gettoned
            }
        }
    },
    LOSE_WEIGHT {
        override fun getDisplayName(context: Context, gender: Gender): String =
            context.getString(R.string.onboarding_goal_lose_weight)

        override fun getImageResource(context: Context, gender: Gender): Int {
            return when (gender) {
                Gender.MALE -> R.drawable.img_onboarding_goal_male_loseweight
                Gender.FEMALE -> R.drawable.img_onboarding_goal_female_loseweight
            }
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

    override fun iterator(): Iterator<OnboaringGoal> = enumValues<OnboaringGoal>().iterator()
}