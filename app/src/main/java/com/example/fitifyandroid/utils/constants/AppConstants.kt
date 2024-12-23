package com.example.fitifyandroid.utils.constants

import android.content.Context
import com.example.fitifyandroid.R

class AppConstants {
    companion object {
        const val SHARED_PREFERENCES_NAME = "Fitify"
        const val IS_LOGGED_IN_SP_KEY = "isLoggedIn"
        const val PASSWORD_MINIMUM_CHARACTERS = 6
        const val PASSWORD_MAXIMUM_CHARACTERS = 32
        const val EMAIL_MAXIMUM_CHARACTERS = 255
        const val WEIGHT_DECIMAL_DIGITS = 1
        const val MAX_WEIGHT = 300f
        const val MAX_HEIGHT = 250
        const val MIN_HEIGHT = 70
        const val MIN_WEIGHT = 10f
        const val MAX_AGE = 99
        const val MIN_AGE = 10

        fun emailInvalidMessage(context: Context): String {
            return context.getString(R.string.error_invalid_email)
        }

        fun passwordShortMessage(context: Context): String {
            return context.getString(R.string.error_short_password)
        }
    }
}