package com.example.fitifyandroid.utils.extensions

import android.animation.ValueAnimator
import com.airbnb.lottie.LottieAnimationView

fun LottieAnimationView.play(currentProgress: Float, targetProgress: Float) {
    val animator = ValueAnimator.ofFloat(currentProgress, targetProgress)
    animator.addUpdateListener {
        val progress = it.animatedValue as Float
        this.progress = progress
    }
    animator.start()
}