package com.example.learnandroid.presentation.components.bottomAccentGradientButton

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.learnandroid.R

class BottomAccentGradientButton(context: Context, attrs: AttributeSet): ConstraintLayout(context, attrs) {
    init {
        LayoutInflater.from(context).inflate(R.layout.bottom_accent_gradient_button, this, true)
        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.BottomAccentGradientButton)
        val title = typedArray.getString(R.styleable.BottomAccentGradientButton_title)

        val titleTextView = findViewById<TextView>(R.id.titleTextView)
        titleTextView.text = title
        typedArray.recycle()
    }

    override fun setEnabled(enabled: Boolean) {
        super.setEnabled(enabled)
        alpha = if (enabled) 1f else 0.3f
    }
}