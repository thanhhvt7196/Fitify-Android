package com.example.learnandroid.domain.models

import android.content.Context
import com.example.learnandroid.R

enum class PushUp {
    MORE_THAN_30 {
        override fun getTitle(context: Context): String {
            return String.format(context.getString(R.string.onboarding_push_ups_more_than_x), 30)
        }
    },
    FIFTEEN_TO_TWENTY_NINE {
        override fun getTitle(context: Context): String {
            return String.format(context.getString(R.string.onboarding_push_ups_range_format), 15, 29)
        }
    },
    SIX_TO_FOURTEEN {
        override fun getTitle(context: Context): String {
            return String.format(context.getString(R.string.onboarding_push_ups_range_format), 6, 14)
        }
    },
    LESS_THAN_FIVE {
        override fun getTitle(context: Context): String {
            return String.format(context.getString(R.string.onboarding_push_ups_less_than_x), 5)
        }
    };

    abstract fun getTitle(context: Context): String
}