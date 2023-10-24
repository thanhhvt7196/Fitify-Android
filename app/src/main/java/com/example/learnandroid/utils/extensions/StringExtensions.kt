package com.example.learnandroid.utils.extensions

import android.util.Patterns

fun String.isEmail(): Boolean {
    return Patterns.EMAIL_ADDRESS.matcher(this).matches()
}