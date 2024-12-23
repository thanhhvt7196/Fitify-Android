package com.example.fitifyandroid.utils.log

import com.example.fitifyandroid.BuildConfig

object Logger {
    private const val LOGGABLE = BuildConfig.LOGGABLE

    fun log(message: String) {
        if (LOGGABLE) {
            com.orhanobut.logger.Logger.d(message)
        }
    }

    fun json(jsonObject: String) {
        if (LOGGABLE) {
            com.orhanobut.logger.Logger.json(jsonObject)
        }
    }
}