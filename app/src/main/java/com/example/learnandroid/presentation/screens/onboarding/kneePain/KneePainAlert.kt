package com.example.learnandroid.presentation.screens.onboarding.kneePain

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.learnandroid.R
import com.example.learnandroid.presentation.components.bottomAccentGradientButton.BottomAccentGradientButton

class KneePainAlert(context: Context, attrs: AttributeSet?): ConstraintLayout(context, attrs) {
    interface KneePainAlertDelegate {
        fun didContinueTapped()
    }

    private var delegate: KneePainAlertDelegate? = null

    init {
        LayoutInflater.from(context).inflate(R.layout.knee_pain_alert_view, this, true)

        val continueButton = findViewById<BottomAccentGradientButton>(R.id.continueButton)
        continueButton.setOnClickListener {
            delegate?.didContinueTapped()
        }
    }

    fun setAction(delegate: KneePainAlertDelegate) {
        this.delegate = delegate
    }

    override fun onDetachedFromWindow() {
        super.onDetachedFromWindow()
        delegate = null
    }
}