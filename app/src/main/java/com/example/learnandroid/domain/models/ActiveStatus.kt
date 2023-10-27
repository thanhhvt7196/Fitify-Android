package com.example.learnandroid.domain.models

import android.content.Context
import com.example.learnandroid.R

enum class ActiveStatus {
    ACTIVE {
        override fun getTitle(context: Context): String {
            return context.getString(R.string.onboarding_typical_day_mostly_active)
        }
    },
    ON_FOOT {
        override fun getTitle(context: Context): String {
            return context.getString(R.string.onboarding_typical_day_mostly_on_foot)
        }
    },
    SEATED {
        override fun getTitle(context: Context): String {
            return context.getString(R.string.onboarding_typical_day_mostly_seated)
        }
    },
    INACTIVE {
        override fun getTitle(context: Context): String {
            return context.getString(R.string.onboarding_typical_day_mostly_inactive)
        }
    };

    abstract fun getTitle(context: Context): String
}