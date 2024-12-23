package com.example.fitifyandroid.presentation.screens.profile.views

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.fitifyandroid.R

class ProfileHeaderStatView(context: Context, attrs: AttributeSet): ConstraintLayout(context, attrs) {
    init {
        LayoutInflater.from(context).inflate(R.layout.profile_header_stat_view, this, true)
        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.ProfileHeaderStatView)
        val title = typedArray.getString(R.styleable.ProfileHeaderStatView_title) ?: ""
        val subtitle = typedArray.getString(R.styleable.ProfileHeaderStatView_subtitle) ?: ""
        val image = typedArray.getResourceId(R.styleable.ProfileHeaderStatView_image, 0)
        typedArray.recycle()

        val titleTextView = findViewById<TextView>(R.id.titleTextView)
        titleTextView.text = title

        val subtitleTextView = findViewById<TextView>(R.id.subtitleTextView)
        subtitleTextView.text = subtitle

        val iconImageView = findViewById<ImageView>(R.id.iconImageView)
        if (image != 0) {
            iconImageView.setImageResource(image)
        }
    }
}