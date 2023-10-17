package com.example.learnandroid.presentation.screens.profile.views

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.learnandroid.R

class ProfileHeaderStatItem(context: Context, attrs: AttributeSet): ConstraintLayout(context, attrs) {

    init {
        LayoutInflater.from(context).inflate(R.layout.profile_header_stat_item, this, true)
        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.ProfileHeaderStatItem)
        val title = typedArray.getString(R.styleable.ProfileHeaderStatItem_title)
        val value = typedArray.getString(R.styleable.ProfileHeaderStatItem_count)
        val image = typedArray.getResourceId(R.styleable.ProfileHeaderStatItem_image, 0)
        typedArray.recycle()

        val titleTextView = findViewById<TextView>(R.id.titleTextView)
        titleTextView.text = title

        val valueTextView = findViewById<TextView>(R.id.valueTextView)
        valueTextView.text = value

        if (image != 0) {
            val iconImageView = findViewById<ImageView>(R.id.iconImageView)
            iconImageView.setImageResource(image)
        }
    }
}