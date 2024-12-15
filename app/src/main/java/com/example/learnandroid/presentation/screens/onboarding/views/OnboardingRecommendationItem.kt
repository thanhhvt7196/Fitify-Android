package com.example.learnandroid.presentation.screens.onboarding.views

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.content.res.AppCompatResources
import androidx.appcompat.widget.LinearLayoutCompat
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.isVisible
import com.example.learnandroid.R

class OnboardingRecommendationItem(context: Context, attrs: AttributeSet) :
    ConstraintLayout(context, attrs) {
    init {
        LayoutInflater.from(context).inflate(R.layout.onboarding_recommendation_item, this, true)
    }

    fun config(title: String, isRecommended: Boolean = false) {
        val titleTextView = findViewById<TextView>(R.id.titleTextView)
        titleTextView.text = title

        val recommendationView = findViewById<LinearLayoutCompat>(R.id.recommendationTextView)
        recommendationView.isVisible = isRecommended
    }

    override fun setSelected(selected: Boolean) {
        super.setSelected(selected)
        val checkImageView = findViewById<ImageView>(R.id.checkImageView)
        checkImageView.isVisible = isSelected
        val containerView = findViewById<ConstraintLayout>(R.id.containerView)
        containerView.background =
            AppCompatResources.getDrawable(
                context,
                if (selected) R.drawable.gradient_border else R.drawable.blue_base_border
            )
    }
}