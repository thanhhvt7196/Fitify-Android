package com.example.learnandroid.presentation.components.roundedFloatingTextField

import android.content.Context
import android.graphics.Color
import android.text.InputFilter
import android.text.InputType
import android.text.TextWatcher
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.cardview.widget.CardView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.core.widget.addTextChangedListener
import com.example.learnandroid.R
import com.google.android.material.card.MaterialCardView
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout

class RoundedFloatingTextField(context: Context, attrs: AttributeSet) :
    ConstraintLayout(context, attrs) {
    init {
        LayoutInflater.from(context).inflate(R.layout.rounded_floating_text_field, this, true)

        val textInputLayout = findViewById<TextInputLayout>(R.id.textInputLayout)
        textInputLayout.setHintTextAppearance(R.style.floating_text_field_hint_style)
        textInputLayout.setBackgroundColor(ContextCompat.getColor(context, R.color.blue_dark_0_5))
        textInputLayout.boxBackgroundMode = TextInputLayout.BOX_BACKGROUND_NONE
        val cardView = findViewById<MaterialCardView>(R.id.cardView)
        cardView.strokeColor = ContextCompat.getColor(context, R.color.blue_dark)

        val borderWidthInPixels =
            resources.getDimension(R.dimen.dp_0)
        cardView.cardElevation = borderWidthInPixels

        val textField = findViewById<TextInputEditText>(R.id.textField)
        textField.setOnFocusChangeListener { _, hasFocus ->
            textInputLayout.setBackgroundColor(
                ContextCompat.getColor(
                    context,
                    if (hasFocus) R.color.blue_dark else R.color.blue_dark_0_5
                )
            )

            cardView.strokeColor = ContextCompat.getColor(
                context,
                if (hasFocus) R.color.blue_dark_0_5 else R.color.blue_dark
            )
        }

        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.RoundedFloatingTextField)
        textInputLayout.hint = typedArray.getString(R.styleable.RoundedFloatingTextField_placeholder)
        typedArray.recycle()
    }

    fun setTextChangeHandler(handler: (text:String) -> Unit) {
        val textField = findViewById<TextInputEditText>(R.id.textField)
        textField.addTextChangedListener { editable ->
            editable?.let {
                handler(it.toString())
            } ?: handler("")
        }
    }

    fun getText(): String {
        val textField = findViewById<TextInputEditText>(R.id.textField)
        return textField.text?.let {
            it.toString()
        } ?: ""
    }

    fun setKeyboardType(keyboardType: Int) {
        val textField = findViewById<TextInputEditText>(R.id.textField)
        textField.inputType = keyboardType
    }

    fun setPlaceholder(placeholder: String) {
        val textInputLayout = findViewById<TextInputLayout>(R.id.textInputLayout)
        textInputLayout.hint = placeholder
    }

    fun setMaxLength(maxLength: Int) {
        val textField = findViewById<TextInputEditText>(R.id.textField)

        val filter = Array<InputFilter>(1) {
            InputFilter.LengthFilter(maxLength)
        }
        textField.filters = filter
    }
}