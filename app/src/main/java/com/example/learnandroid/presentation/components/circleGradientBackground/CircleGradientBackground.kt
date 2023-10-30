package com.example.learnandroid.presentation.components.circleGradientBackground

import android.content.Context
import android.graphics.drawable.GradientDrawable
import androidx.core.content.ContextCompat
import com.example.learnandroid.R

class CircleGradientBackground(context: Context) :
    GradientDrawable() {
    init {
        orientation = Orientation.TOP_BOTTOM
        colors = intArrayOf(
            ContextCompat.getColor(context, R.color.vibrant_gradient_start),
            ContextCompat.getColor(context, R.color.vibrant_gradient_end)
        )
        shape = OVAL
    }
}