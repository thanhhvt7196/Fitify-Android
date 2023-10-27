package com.example.learnandroid.domain.models

import android.content.Context
import com.example.learnandroid.R

enum class BadHabit {
    SWEET_TOOTH {
        override fun getTitle(context: Context): String {
            return context.getString(R.string.onboarding_bad_habit_sweet)
        }
    },
    SUGARY_DRINKS {
        override fun getTitle(context: Context): String {
            return context.getString(R.string.onboarding_bad_habit_sugary_drinks)
        }
    },
    SLEEPLESS {
        override fun getTitle(context: Context): String {
            return context.getString(R.string.onboarding_bad_habit_sleep)
        }
    },
    FAST_FOOD {
        override fun getTitle(context: Context): String {
            return context.getString(R.string.onboarding_bad_habit_fast_food)
        }
    },
    EATING_LATE {
        override fun getTitle(context: Context): String {
            return context.getString(R.string.onboarding_bad_habit_eating_late)
        }
    };

    abstract fun getTitle(context: Context): String
}