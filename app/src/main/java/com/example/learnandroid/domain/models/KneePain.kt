package com.example.learnandroid.domain.models

import android.content.Context
import com.example.learnandroid.R

enum class KneePain {
    NO {
        override fun getTitle(context: Context): String {
            return context.getString(R.string.no)
        }

        override fun subtitle(context: Context): String? {
            return null
        }

        override fun message(context: Context): String? {
            return null
        }
    },
    MILD_PAIN {
        override fun getTitle(context: Context): String {
            return context.getString(R.string.yes)
        }

        override fun subtitle(context: Context): String? {
            return context.getString(R.string.knee_pain_mild)
        }

        override fun message(context: Context): String? {
            return context.getString(R.string.knee_pain_mild_description)
        }
    },
    QUITE_SERIOUS {
        override fun getTitle(context: Context): String {
            return context.getString(R.string.yes)
        }

        override fun subtitle(context: Context): String? {
            return context.getString(R.string.knee_pain_serious)
        }

        override fun message(context: Context): String? {
            return context.getString(R.string.knee_pain_serious_description)
        }
    };

    abstract fun getTitle(context: Context): String
    abstract fun subtitle(context: Context): String?
    abstract fun message(context: Context): String?
}