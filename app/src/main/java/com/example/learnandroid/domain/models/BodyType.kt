package com.example.learnandroid.domain.models

import android.content.Context
import com.example.learnandroid.R

enum class BodyType : Iterable<BodyType> {
    SKINNY {
        override fun getDisplayName(context: Context, gender: Gender): String {
            return when (gender) {
                Gender.FEMALE -> context.getString(R.string.onboarding_body_type_hourglass)
                Gender.MALE -> context.getString(R.string.onboarding_body_type_skinny)
            }
        }

        override fun getImageResource(context: Context, gender: Gender): Int {
            return when (gender) {
                Gender.MALE -> R.drawable.onboarding_body_skinny
                Gender.FEMALE -> R.drawable.onboarding_body_hourglass
            }
        }
    },
    IDEAL {
        override fun getDisplayName(context: Context, gender: Gender): String {
            return when (gender) {
                Gender.MALE -> context.getString(R.string.onboarding_body_type_ideal)
                Gender.FEMALE -> context.getString(R.string.onboarding_body_type_rectangle)
            }
        }

        override fun getImageResource(context: Context, gender: Gender): Int {
            return when (gender) {
                Gender.MALE -> R.drawable.onboarding_body_ideal
                Gender.FEMALE -> R.drawable.onboarding_body_rectangle
            }
        }
    },
    FLABBY {
        override fun getDisplayName(context: Context, gender: Gender): String {
            return when (gender) {
                Gender.MALE -> context.getString(R.string.onboarding_body_type_flabby)
                Gender.FEMALE -> context.getString(R.string.onboarding_body_type_rounded)
            }
        }

        override fun getImageResource(context: Context, gender: Gender): Int {
            return when (gender) {
                Gender.MALE -> R.drawable.onboarding_body_flabby
                Gender.FEMALE -> R.drawable.onboarding_body_rounded
            }
        }
    },
    HEAVIER {
        override fun getDisplayName(context: Context, gender: Gender): String {
            return when (gender) {
                Gender.MALE -> context.getString(R.string.onboarding_body_type_heavier)
                Gender.FEMALE -> context.getString(R.string.onboarding_body_type_lightbulb)
            }
        }

        override fun getImageResource(context: Context, gender: Gender): Int {
            return when (gender) {
                Gender.MALE -> R.drawable.onboarding_body_heavier
                Gender.FEMALE -> R.drawable.onboarding_body_lightbulb
            }
        }
    };


    abstract fun getDisplayName(context: Context, gender: Gender): String
    abstract fun getImageResource(context: Context, gender: Gender): Int

    override fun iterator(): Iterator<BodyType> = enumValues<BodyType>().iterator()
}