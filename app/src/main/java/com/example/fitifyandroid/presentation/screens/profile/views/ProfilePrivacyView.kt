package com.example.fitifyandroid.presentation.screens.profile.views

import android.content.Context
import android.text.SpannableString
import android.text.Spanned
import android.text.method.LinkMovementMethod
import android.text.style.ClickableSpan
import android.text.style.ForegroundColorSpan
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import com.example.fitifyandroid.R

class ProfilePrivacyView(context: Context, attrs: AttributeSet): ConstraintLayout(context, attrs) {
    init {
        LayoutInflater.from(context).inflate(R.layout.profile_privacy_view, this, true)
        val privacyTextView = findViewById<TextView>(R.id.privacyTextView)

        val termsOfService = context.getString(R.string.login_terms)
        val privacyPolicy = context.getString(R.string.privacy_policy)
        val disclaimer = context.getString(R.string.disclaimer)

        val fullString = String.format(
            "%s, %s, %s",
            termsOfService,
            privacyPolicy,
            disclaimer
        )

        val spannableString = SpannableString(fullString)

        val clickableKeywords = arrayOf(termsOfService, privacyPolicy, disclaimer)

        for (keyword in clickableKeywords) {
            val startIndex = fullString.indexOf(keyword)
            val endIndex = startIndex + keyword.length

            if (startIndex != -1) {
                val clickableSpan = object : ClickableSpan() {
                    override fun onClick(widget: View) {
                        Toast.makeText(context, "$keyword clicked", Toast.LENGTH_SHORT).show()
                    }
                }
                spannableString.setSpan(clickableSpan, startIndex, endIndex, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
                spannableString.setSpan(ForegroundColorSpan(ContextCompat.getColor(context, R.color.blue_light_3)), startIndex, endIndex, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
            }
        }

        privacyTextView.text = spannableString
        privacyTextView.movementMethod = LinkMovementMethod.getInstance()
    }
}