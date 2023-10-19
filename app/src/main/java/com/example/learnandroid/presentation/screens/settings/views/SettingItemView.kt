package com.example.learnandroid.presentation.screens.settings.views

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.learnandroid.R

class SettingItemView(context: Context, attrs: AttributeSet): ConstraintLayout(context, attrs) {
    init {
        LayoutInflater.from(context).inflate(R.layout.setting_item_view, this, true)
        val typedArray = context.obtainStyledAttributes(R.styleable.SettingItemView)
        val title = typedArray.getString(R.styleable.SettingItemView_title)
        val image = typedArray.getResourceId(R.styleable.SettingItemView_image, 0)

        val iconImageView = findViewById<ImageView>(R.id.iconImageView)
        val titleTextView = findViewById<TextView>(R.id.titleTextView)
        titleTextView.text = title

        if (image != 0) {
            iconImageView.setImageResource(image)
        }
    }
}