package com.example.learnandroid.presentation.screens.onboarding.views

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.isVisible
import com.example.learnandroid.R

class OnboardingTextItemView(context: Context, attrs: AttributeSet?) :
    ConstraintLayout(context, attrs) {
    init {
        LayoutInflater.from(context).inflate(R.layout.onboarding_text_item_view, this, true)
    }

    fun config(title: String, description: String? = null) {
        val titleTextView = findViewById<TextView>(R.id.titleTextView)
        val descriptionTextView = findViewById<TextView>(R.id.descriptionTextView)

        titleTextView.text = title
        descriptionTextView.text = description
        descriptionTextView.isVisible = (description ?: "").trim().isNotEmpty()
    }

    override fun setSelected(selected: Boolean) {
        super.setSelected(selected)
        val checkImageView = findViewById<ImageView>(R.id.checkImageView)
        checkImageView.isVisible = isSelected
        val containerView = findViewById<ConstraintLayout>(R.id.containerView)
        containerView.background =
            context.getDrawable(if (selected) R.drawable.gradient_border else R.drawable.blue_base_border)
    }
}