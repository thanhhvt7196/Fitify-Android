package com.example.learnandroid.domain.models

import android.content.Context
import com.example.learnandroid.R

enum class WorkoutFrequency {
    MORE_THAN_FOUR {
        override fun getTitle(context: Context): String {
            return String.format(context.getString(R.string.onboarding_workout_frequency_more_than_x_a_week), 4)
        }
    },
    THREE_OR_FOUR {
        override fun getTitle(context: Context): String {
            return String.format(context.getString(R.string.onboarding_workout_frequency_x_to_y_weeks), 3, 4)
        }
    },
    ONE_OR_TWO {
        override fun getTitle(context: Context): String {
            return String.format(context.getString(R.string.onboarding_workout_frequency_x_to_y_weeks), 1, 2)
        }
    },
    NEVER {
        override fun getTitle(context: Context): String {
            return context.getString(R.string.onboarding_workout_frequency_never)
        }
    };

    abstract fun getTitle(context: Context): String
}