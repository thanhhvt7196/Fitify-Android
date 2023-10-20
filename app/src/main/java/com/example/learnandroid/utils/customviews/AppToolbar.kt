package com.example.learnandroid.utils.customviews

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.cardview.widget.CardView
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.learnandroid.R

class AppToolbar(context: Context, attrs: AttributeSet): ConstraintLayout(context, attrs) {
    init {
        LayoutInflater.from(context).inflate(R.layout.app_toolbar, this, true)
    }

    override fun setOnClickListener(l: OnClickListener?) {
        val backButton = findViewById<CardView>(R.id.backButton)
        backButton.setOnClickListener(l)
    }
}