package com.example.learnandroid.utils.constants

import com.example.learnandroid.FitifyApplication
import com.example.learnandroid.R

class AppConstants {
    companion object {
        const val sharedPreferencesName = "Fitify"
        const val isLoggedInSPKey = "isLoggedIn"
        const val passwordMinimumCharacters = 6
        const val passwordMaximumCharacters = 32
        const val emailMaximumCharacters = 255
        const val weightDecimalDigits = 1
        const val maxWeight = 300f
        const val maxHeight = 250
        const val minHeight = 70
        const val minWeight = 10f
        const val maxAge = 99
        const val minAge = 10

        val emailInvalidMessage: String
            get() = FitifyApplication.mContext.getString(R.string.error_invalid_email)
        val passwordShortMessage: String
            get() = FitifyApplication.mContext.getString(R.string.error_short_password)
    }
}