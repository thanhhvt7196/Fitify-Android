package com.example.fitifyandroid.presentation.screens.settings.views

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.isVisible
import com.example.fitifyandroid.R

class SettingItemView(context: Context, attrs: AttributeSet) : ConstraintLayout(context, attrs) {
    init {
        LayoutInflater.from(context).inflate(R.layout.setting_item_view, this, true)
        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.SettingItemView)
        val title = typedArray.getString(R.styleable.SettingItemView_title)
        val image = typedArray.getResourceId(R.styleable.SettingItemView_image, 0)
        val showSeparator = typedArray.getBoolean(R.styleable.SettingItemView_showSeparator, true)
        typedArray.recycle()

        val iconImageView = findViewById<ImageView>(R.id.iconImageView)
        val titleTextView = findViewById<TextView>(R.id.titleTextView)
        val separator = findViewById<View>(R.id.separator)
        titleTextView.text = title
        separator.isVisible = showSeparator
        if (image != 0) {
            iconImageView.setImageResource(image)
        }
    }
}