package com.example.learnandroid.utils

import android.content.res.Resources
import android.graphics.Bitmap
import android.graphics.PorterDuff
import android.graphics.*
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable

object BitmapExtension {
    fun blendBitmapWithColor(res: Resources, resId: Int, tintColor: Int): Drawable {
        val bitmap = BitmapFactory.decodeResource(res, resId)
        val bitmapDrawable = BitmapDrawable(res, bitmap)

        val colorFilter = PorterDuffColorFilter(tintColor, PorterDuff.Mode.MULTIPLY)
        bitmapDrawable.colorFilter = colorFilter
        return  bitmapDrawable
    }
}