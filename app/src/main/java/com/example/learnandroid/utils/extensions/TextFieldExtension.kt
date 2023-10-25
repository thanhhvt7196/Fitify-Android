package com.example.learnandroid.utils.extensions

import android.app.Activity
import android.content.Context
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat
import com.google.android.material.textfield.TextInputEditText

fun TextInputEditText.focus(context: Context) {
    requestFocus()
    (context as Activity?)?.let {
        WindowInsetsControllerCompat(it.window, this).show(WindowInsetsCompat.Type.ime())
    }
}

fun TextInputEditText.unfocus(context: Context) {
    clearFocus()
    (context as Activity?)?.let {
        WindowInsetsControllerCompat(it.window, this).hide(WindowInsetsCompat.Type.ime())
    }
}