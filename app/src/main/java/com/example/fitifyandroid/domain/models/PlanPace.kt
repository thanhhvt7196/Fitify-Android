package com.example.fitifyandroid.domain.models

import android.content.Context
import com.example.fitifyandroid.R

enum class PlanPace {
    FAST {
        override fun getTitle(context: Context): String {
            return context.getString(R.string.onboarding_plan_pace_fast)
        }
    },
    BALANCED {
        override fun getTitle(context: Context): String {
            return context.getString(R.string.onboarding_plan_pace_balanced)
        }
    },
    SLOW {
        override fun getTitle(context: Context): String {
            return context.getString(R.string.onboarding_plan_pace_slow)
        }
    };

    abstract fun getTitle(context: Context): String
}