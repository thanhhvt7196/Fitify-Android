package com.example.fitifyandroid.domain.models

import android.content.Context
import com.example.fitifyandroid.R

enum class Gender : Iterable<Gender> {
    MALE {
        override fun getDisplayName(context: Context): String =
            context.getString(R.string.onboarding_gender_male)

        override fun getImageResource(context: Context): Int {
            return R.drawable.onboarding_gender_male
        }
    },
    FEMALE {
        override fun getDisplayName(context: Context): String =
            context.getString(R.string.onboarding_gender_female)

        override fun getImageResource(context: Context): Int {
            return R.drawable.onboarding_gender_female
        }
    };

    abstract fun getDisplayName(context: Context): String
    abstract fun getImageResource(context: Context): Int

    override fun iterator(): Iterator<Gender> = enumValues<Gender>().iterator()
}