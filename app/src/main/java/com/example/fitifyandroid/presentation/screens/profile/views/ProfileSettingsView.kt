package com.example.fitifyandroid.presentation.screens.profile.views

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.fitifyandroid.R

class ProfileSettingsView(context: Context, attrs: AttributeSet): ConstraintLayout(context, attrs) {
    init {
        LayoutInflater.from(context).inflate(R.layout.profile_settings_view, this, true)
    }
}