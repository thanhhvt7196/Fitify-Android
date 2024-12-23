package com.example.fitifyandroid.presentation.components.appToolBar

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.fitifyandroid.R

class AppToolbar(context: Context, attrs: AttributeSet): ConstraintLayout(context, attrs) {
    enum class BackButtonType {
        POP,
        DISMISS;
    }

    init {
        LayoutInflater.from(context).inflate(R.layout.app_toolbar, this, true)
    }

    fun setTitle(title: String) {
        val titleTextView = findViewById<TextView>(R.id.titleTextView)
        titleTextView.text = title
    }

    fun setBackButtonType(type: BackButtonType) {
        val iconImageView = findViewById<ImageView>(R.id.iconImageView)
        when (type) {
            BackButtonType.POP -> {
                iconImageView.setImageResource(R.drawable.chevron_left)
            }
            BackButtonType.DISMISS -> {
                iconImageView.setImageResource(R.drawable.dismiss)
            }
        }
    }

    fun setBackButtonOnClickListener(l: OnClickListener?) {
        val backButton = findViewById<CardView>(R.id.backButton)
        backButton.setOnClickListener(l)
    }
}