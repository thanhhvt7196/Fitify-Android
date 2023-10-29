package com.example.learnandroid.domain.models

import android.content.Context
import com.example.learnandroid.R

enum class Source: Iterable<Source> {
    GOOGLE_PLAY_STORE {
        override fun getTitle(context: Context): String {
            return context.getString(R.string.onboarding_source_google_play)
        }
    },
    TV {
        override fun getTitle(context: Context): String {
            return context.getString(R.string.onboarding_source_tv)
        }
    },
    RADIO {
        override fun getTitle(context: Context): String {
            return context.getString(R.string.onboarding_source_radio_podcast)
        }
    },
    FRIENDS {
        override fun getTitle(context: Context): String {
            return context.getString(R.string.onboarding_source_friends_family)
        }
    },
    FACEBOOK {
        override fun getTitle(context: Context): String {
            return context.getString(R.string.onboarding_source_facebook)
        }
    },
    INSTAGRAM {
        override fun getTitle(context: Context): String {
            return context.getString(R.string.onboarding_source_instagram)
        }
    },
    GOOGLE_SEARCH {
        override fun getTitle(context: Context): String {
            return context.getString(R.string.onboarding_source_google_search)
        }
    },
    YOUTUBE {
        override fun getTitle(context: Context): String {
            return context.getString(R.string.onboarding_source_youtube)
        }
    },
    TIKTOK {
        override fun getTitle(context: Context): String {
            return context.getString(R.string.onboarding_source_tiktok)
        }
    },
    PINTEREST {
        override fun getTitle(context: Context): String {
            return context.getString(R.string.onboarding_source_pinterest)
        }
    },
    OTHER {
        override fun getTitle(context: Context): String {
            return context.getString(R.string.onboarding_source_other)
        }
    };

    abstract fun getTitle(context: Context): String
    override fun iterator(): Iterator<Source> = enumValues<Source>().iterator()
}