package com.example.learnandroid.presentation.components.bottomWhiteButton

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.learnandroid.R

class BottomWhiteButton(context: Context, attrs: AttributeSet): ConstraintLayout(context, attrs) {
    init {
        LayoutInflater.from(context).inflate(R.layout.bottom_white_button, this, true)
        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.BottomWhiteButton)
        val title = typedArray.getString(R.styleable.BottomWhiteButton_title)

        val titleTextView = findViewById<TextView>(R.id.titleTextView)
        titleTextView.text = title
        typedArray.recycle()
    }
}