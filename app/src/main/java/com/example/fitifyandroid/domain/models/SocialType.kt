package com.example.fitifyandroid.domain.models

import android.content.Context
import com.example.fitifyandroid.R

enum class SocialType: Iterable<SocialType> {
    APPLE {
        override fun getIconResource(context: Context): Int {
            return R.drawable.apple_logo
        }
    },
    GOOGLE {
        override fun getIconResource(context: Context): Int {
            return R.drawable.google_icon
        }
    },
    FACEBOOK {
        override fun getIconResource(context: Context): Int {
            return R.drawable.facebook
        }
    };

    abstract fun getIconResource(context: Context): Int

    override fun iterator(): Iterator<SocialType> = enumValues<SocialType>().iterator()
}