package com.example.fitifyandroid.presentation.screens.onboarding.views

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import com.example.fitifyandroid.R
import com.example.fitifyandroid.domain.models.SocialType

class SocialLoginButton(context: Context, attrs: AttributeSet): ConstraintLayout(context, attrs) {
    init {
        LayoutInflater.from(context).inflate(R.layout.social_login_button, this, true)
    }

    fun config(socialType: SocialType) {
        val titleTextView = findViewById<TextView>(R.id.titleTextView)
        val containerView = findViewById<ConstraintLayout>(R.id.containerView)
        val iconImageView = findViewById<ImageView>(R.id.iconImageView)
        iconImageView.setImageResource(socialType.getIconResource(context))

        when (socialType) {
            SocialType.APPLE -> {
                titleTextView.text = context.getString(R.string.login_apple_title)
                titleTextView.setTextColor(ContextCompat.getColor(context, R.color.black))
                containerView.setBackgroundColor(ContextCompat.getColor(context, R.color.essential_white))
            }
            SocialType.GOOGLE -> {
                titleTextView.text = context.getString(R.string.login_google_title)
                titleTextView.setTextColor(ContextCompat.getColor(context, R.color.black))
                containerView.setBackgroundColor(ContextCompat.getColor(context, R.color.essential_white))
            }
            SocialType.FACEBOOK -> {
                titleTextView.text = context.getString(R.string.login_facebook_title)
                titleTextView.setTextColor(ContextCompat.getColor(context, R.color.essential_white))
                containerView.setBackgroundColor(ContextCompat.getColor(context, R.color.tooltip_blue))
                iconImageView.setColorFilter(ContextCompat.getColor(context, R.color.essential_white))
            }
        }
    }
}