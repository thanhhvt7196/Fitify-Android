package com.example.learnandroid.utils.extensions

import android.app.Activity
import android.content.Context
import android.widget.EditText
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat

fun EditText.focus(context: Context) {
    requestFocus()
    (context as Activity?)?.let {
        WindowInsetsControllerCompat(it.window, this).show(WindowInsetsCompat.Type.ime())
    }
}

fun EditText.unFocus(context: Context) {
    clearFocus()
    (context as Activity?)?.let {
        WindowInsetsControllerCompat(it.window, this).hide(WindowInsetsCompat.Type.ime())
    }
}