package com.example.learnandroid.presentation.screens.onboarding.views

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.learnandroid.R

class RoundedBorderButton(context: Context, attrs: AttributeSet) :
    ConstraintLayout(context, attrs) {
    init {
        LayoutInflater.from(context).inflate(R.layout.rounded_border_button, this, true)
        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.RoundedBorderButton)
        val title = typedArray.getString(R.styleable.RoundedBorderButton_title)
        typedArray.recycle()

        val titleTextView = findViewById<TextView>(R.id.titleTextView)
        titleTextView.text = title
    }
}