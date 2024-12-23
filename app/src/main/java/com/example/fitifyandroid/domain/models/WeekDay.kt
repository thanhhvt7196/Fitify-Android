package com.example.fitifyandroid.domain.models

import android.content.Context
import com.example.fitifyandroid.R
import java.time.DayOfWeek
import java.time.LocalDate

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

    fun isToday(): Boolean {
        return when (LocalDate.now().dayOfWeek) {
            DayOfWeek.MONDAY -> this == MONDAY
            DayOfWeek.TUESDAY -> this == TUESDAY
            DayOfWeek.WEDNESDAY -> this == WEDNESDAY
            DayOfWeek.THURSDAY -> this == THURSDAY
            DayOfWeek.FRIDAY -> this == FRIDAY
            DayOfWeek.SATURDAY -> this == SATURDAY
            DayOfWeek.SUNDAY -> this == SUNDAY
            else -> false
        }
    }
}