package com.example.learnandroid.domain.models

import android.content.Context
import com.example.learnandroid.R

enum class FitnessTool: Iterable<FitnessTool> {
    DUMBBELL {
        override fun getTitle(context: Context): String {
            return context.getString(R.string.tool_dumbbell)
        }
    },
    BARBELL {
        override fun getTitle(context: Context): String {
            return context.getString(R.string.tool_barbell)
        }
    },
    KETTLEBELL {
        override fun getTitle(context: Context): String {
            return context.getString(R.string.tool_kettlebell)
        }
    },
    RESISTANCE_BAND {
        override fun getTitle(context: Context): String {
            return context.getString(R.string.tool_resistanceband)
        }
    },
    PULL_UP_BAR {
        override fun getTitle(context: Context): String {
            return context.getString(R.string.tool_pullupbar)
        }
    },
    FOAM_ROLLER {
        override fun getTitle(context: Context): String {
            return context.getString(R.string.tool_foamroller)
        }
    },
    SWISS_BALL {
        override fun getTitle(context: Context): String {
            return context.getString(R.string.tool_swissball)
        }
    },
    TRX {
        override fun getTitle(context: Context): String {
            return context.getString(R.string.tool_trx_v2)
        }
    },
    BOSU {
        override fun getTitle(context: Context): String {
            return context.getString(R.string.tool_bosu)
        }
    },
    MEDICINE_BALL {
        override fun getTitle(context: Context): String {
            return context.getString(R.string.tool_medicineball)
        }
    },
    SANDBAG {
        override fun getTitle(context: Context): String {
            return context.getString(R.string.tool_sandbag)
        }
    };

    abstract fun getTitle(context: Context): String
    override fun iterator(): Iterator<FitnessTool> = enumValues<FitnessTool>().iterator()
}