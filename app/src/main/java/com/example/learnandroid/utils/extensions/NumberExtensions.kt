package com.example.learnandroid.utils.extensions

import java.text.DecimalFormat
import kotlin.math.pow
import kotlin.math.roundToInt

fun Double.roundTo(decimals: Int = 0): Double {
    val factor = 10.0.pow(decimals.toDouble())
    return (this * factor).roundToInt() / factor
}

fun Float.roundTo(decimals: Int = 0): Float {
    val factor = 10.0f.pow(decimals.toFloat())
    return (this * factor).roundToInt() / factor
}

val String.isFloat: Boolean get() = this.toFloatOrNull() != null
val String.isInt: Boolean get() = this.toIntOrNull() != null

fun String.countDecimalPlaces(): Int? {
    return if (isFloat) {
        val decimalIndex = this.indexOfFirst { it == '.' }
        if (decimalIndex >= 0) {
            this.length - decimalIndex - 1
        } else {
            0
        }
    } else {
        null
    }
}

fun Float.toStringWithoutTrailingZero(): String {
    val format = DecimalFormat("#.#")
    return format.format(this)
}