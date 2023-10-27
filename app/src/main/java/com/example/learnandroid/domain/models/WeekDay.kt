package com.example.learnandroid.domain.models

import android.content.Context

enum class WeekDay {
    MONDAY {
        override fun getShortName(context: Context): String {
            return "Mon"
        }
    },
    TUESDAY {
        override fun getShortName(context: Context): String {
            return "Tue"
        }
    },
    WEDNESDAY {
        override fun getShortName(context: Context): String {
            return "Wed"
        }
    },
    THURSDAY {
        override fun getShortName(context: Context): String {
            return "Thu"
        }
    },
    FRIDAY {
        override fun getShortName(context: Context): String {
            return "Fri"
        }
    },
    SATURDAY {
        override fun getShortName(context: Context): String {
            return "Sat"
        }
    },
    SUNDAY {
        override fun getShortName(context: Context): String {
            return "Sun"
        }
    };

    abstract fun getShortName(context: Context): String
}