package com.example.learnandroid.presentation.screens.onboarding.views

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.learnandroid.R
import com.google.android.material.card.MaterialCardView

class OnboardingItemWithImageView(context: Context, attrs: AttributeSet) :
    ConstraintLayout(context, attrs) {
    init {
        LayoutInflater.from(context).inflate(R.layout.onboarding_item_with_image_view, this, true)
        val typedArray =
            context.obtainStyledAttributes(attrs, R.styleable.OnboardingItemWithImageView)
        val title = typedArray.getString(R.styleable.OnboardingItemWithImageView_title)
        val image = typedArray.getResourceId(R.styleable.OnboardingItemWithImageView_image, 0)
        val borderColor =
            typedArray.getColor(R.styleable.OnboardingItemWithImageView_borderColor, 0)
        typedArray.recycle()

        val titleTextView = findViewById<TextView>(R.id.titleTextView)
        val imageView = findViewById<ImageView>(R.id.imageView)
        val cardView = findViewById<MaterialCardView>(R.id.cardView)
        cardView.strokeColor = borderColor

        titleTextView.text = title
        imageView.setImageResource(image)
    }

    override fun setSelected(isSelected: Boolean) {
        val cardView = findViewById<MaterialCardView>(R.id.cardView)
        cardView.strokeColor =
            if (isSelected) context.getColor(R.color.accent_gradient_end) else context.getColor(R.color.blue_base)
    }
}