package com.example.learnandroid.domain.models

import android.content.Context
import com.example.learnandroid.R

enum class WeekDay {
    MONDAY {
        override fun getShortName(context: Context): String {
            return context.getString(R.string.monday_short)
        }
    },
    TUESDAY {
        override fun getShortName(context: Context): String {
            return context.getString(R.string.tuesday_short)
        }
    },
    WEDNESDAY {
        override fun getShortName(context: Context): String {
            return context.getString(R.string.wednesday_short)
        }
    },
    THURSDAY {
        override fun getShortName(context: Context): String {
            return context.getString(R.string.thursday_short)
        }
    },
    FRIDAY {
        override fun getShortName(context: Context): String {
            return context.getString(R.string.friday_short)
        }
    },
    SATURDAY {
        override fun getShortName(context: Context): String {
            return context.getString(R.string.saturday_short)
        }
    },
    SUNDAY {
        override fun getShortName(context: Context): String {
            return context.getString(R.string.sunday_short)
        }
    };

    abstract fun getShortName(context: Context): String
}