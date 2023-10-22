package com.example.learnandroid.presentation.components.shared.roundedFloatingTextField

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.learnandroid.R

class RoundedFloatingTextField(context: Context, attrs: AttributeSet): ConstraintLayout(context, attrs) {
    init {
        LayoutInflater.from(context).inflate(R.layout.rounded_floating_text_field, this, true)
    }
}