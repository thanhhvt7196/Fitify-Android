package com.example.fitifyandroid.presentation.screens.profile.views

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.fitifyandroid.R

class ProfileFollowUsView(context: Context, attrs: AttributeSet): ConstraintLayout(context, attrs) {
    init {
        LayoutInflater.from(context).inflate(R.layout.profile_follow_us_view, this, true)
    }
}