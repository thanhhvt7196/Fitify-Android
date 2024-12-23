package com.example.learnandroid.presentation.screens.onboarding.views

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.content.res.AppCompatResources
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.isVisible
import com.example.learnandroid.R

class OnboardingTrainingDayItem(context: Context, attrs: AttributeSet?) :
    ConstraintLayout(context, attrs) {
    var isToday: Boolean = false
        set(value) {
            val todayMarker = findViewById<ConstraintLayout>(R.id.todayMarker)
            todayMarker.isVisible = value
            field = value
        }

    init {
        LayoutInflater.from(context).inflate(R.layout.onboarding_training_day_item, this, true)
        val typedArray =
            context.obtainStyledAttributes(attrs, R.styleable.OnboardingTrainingDayItem)
        val title = typedArray.getString(R.styleable.OnboardingTrainingDayItem_title)
        typedArray.recycle()

        val titleTextView = findViewById<TextView>(R.id.textView)
        titleTextView.text = title
    }

    fun config(title: String) {
        val titleTextView = findViewById<TextView>(R.id.textView)

        titleTextView.text = title
    }

    override fun setSelected(selected: Boolean) {
        super.setSelected(selected)
        val checkImageView = findViewById<ImageView>(R.id.checkImageView)
        checkImageView.isVisible = isSelected
        val containerView = findViewById<ConstraintLayout>(R.id.containerView)
        containerView.background = AppCompatResources.getDrawable(
            context,
            if (selected) R.drawable.gradient_border else R.drawable.blue_base_border
        )
    }
}