package com.example.learnandroid.presentation.screens.profile.views

import android.content.Context
import android.graphics.drawable.BitmapDrawable
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.ImageView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import com.example.learnandroid.R
import com.example.learnandroid.utils.BitmapExtension

class ProfileHeaderView(context: Context, attrs: AttributeSet): ConstraintLayout(context, attrs) {
    init {
        LayoutInflater.from(context).inflate(R.layout.profile_header_view, this, true)
        val drawable = ContextCompat.getDrawable(context, R.drawable.profile_bg_photo)
        val bgImageView = findViewById<ImageView>(R.id.imgHeaderBackground)
        if (drawable is BitmapDrawable) {
            val tintColor = ContextCompat.getColor(context, R.color.blue_light_0_5)
            val blendedBitmap = BitmapExtension.blendBitmapWithColor(resources, R.drawable.profile_bg_photo, tintColor)
            blendedBitmap.alpha = 200
            bgImageView.setImageDrawable(blendedBitmap)
        }
    }
}