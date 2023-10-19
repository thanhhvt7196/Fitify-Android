package com.example.learnandroid.presentation.screens.profile.views

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.learnandroid.R

class ProfileMyJourneyStatView(context: Context, attrs: AttributeSet): ConstraintLayout(context, attrs) {
    init {
        LayoutInflater.from(context).inflate(R.layout.profile_my_journey_stat_view, this, true)
    }
}