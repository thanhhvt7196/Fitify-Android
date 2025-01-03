package com.example.fitifyandroid.domain.models

import android.content.Context
import com.example.fitifyandroid.R

enum class DailyWalk {
    MORE_THAN_TWO_HOURS {
        override fun getTitle(context: Context): String {
            return context.resources.getQuantityString(
                R.plurals.onboarding_duration_more_than_x_hours,
                2,
                2
            )
        }
    },
    ONE_TO_TWO_HOURS {
        override fun getTitle(context: Context): String {
            return String.format(context.getString(R.string.onboarding_duration_x_to_y_hours), 1, 2)
        }
    },
    LESS_THAN_AN_HOUR {
        override fun getTitle(context: Context): String {
            return context.resources.getQuantityString(
                R.plurals.onboarding_duration_less_than_x_hours,
                1,
                1
            )
        }
    };

    abstract fun getTitle(context: Context): String
}