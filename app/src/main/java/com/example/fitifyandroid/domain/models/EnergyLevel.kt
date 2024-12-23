package com.example.fitifyandroid.domain.models

import android.content.Context
import com.example.fitifyandroid.R

enum class EnergyLevel {
    STABLE {
        override fun getTitle(context: Context): String {
            return context.getString(R.string.onboarding_energy_level_stable)
        }
    },
    TIRED_LUNCH {
        override fun getTitle(context: Context): String {
            return context.getString(R.string.onboarding_energy_level_tired_around_lunchtime)
        }
    },
    SLEEP_AFTER_MEAL {
        override fun getTitle(context: Context): String {
            return context.getString(R.string.onboarding_energy_level_need_nap_after_meals)
        }
    };

    abstract fun getTitle(context: Context): String
}