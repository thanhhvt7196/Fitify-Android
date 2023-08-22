package com.example.learnandroid.utils.log

import com.example.learnandroid.BuildConfig

object Logger {
    private val LOGGABLE = BuildConfig.LOGGABLE

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