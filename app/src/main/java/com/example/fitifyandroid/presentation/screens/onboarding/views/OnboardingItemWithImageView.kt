package com.example.fitifyandroid.presentation.screens.onboarding.views

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.content.res.AppCompatResources
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.isVisible
import com.example.fitifyandroid.R

class OnboardingItemWithImageView(context: Context, attrs: AttributeSet) :
    ConstraintLayout(context, attrs) {
    init {
        LayoutInflater.from(context).inflate(R.layout.onboarding_item_with_image_view, this, true)
        val typedArray =
            context.obtainStyledAttributes(attrs, R.styleable.OnboardingItemWithImageView)
        val title = typedArray.getString(R.styleable.OnboardingItemWithImageView_title)
        val image = typedArray.getResourceId(R.styleable.OnboardingItemWithImageView_image, 0)
        typedArray.recycle()

        val titleTextView = findViewById<TextView>(R.id.titleTextView)
        val imageView = findViewById<ImageView>(R.id.imageView)

        titleTextView.text = title
        imageView.setImageResource(image)
    }

    override fun setSelected(isSelected: Boolean) {
        val checkImageView = findViewById<ImageView>(R.id.checkImageView)
        checkImageView.isVisible = isSelected
        val containerView = findViewById<ConstraintLayout>(R.id.containerView)
        containerView.background =
            AppCompatResources.getDrawable(
                context,
                if (isSelected) R.drawable.gradient_border else R.drawable.blue_base_border
            )
    }

    fun config(title: String, imageResource: Int) {
        val titleTextView = findViewById<TextView>(R.id.titleTextView)
        titleTextView.text = title

        val imageView = findViewById<ImageView>(R.id.imageView)
        imageView.setImageResource(imageResource)
    }
}