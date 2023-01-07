package com.vikas.shoeapp.util

import androidx.compose.animation.core.CubicBezierEasing
import androidx.compose.animation.core.Easing

object Utils {
    fun lerp(start: Float, stop: Float, fraction: Float): Float =
        (1 - fraction) * start + fraction * stop


    val EaseOutBounce: Easing = Easing { fraction ->
        val n1 = 7.5625f
        val d1 = 2.75f
        var newFraction = fraction

        return@Easing if (newFraction < 1f / d1) {
            n1 * newFraction * newFraction
        } else if (newFraction < 2f / d1) {
            newFraction -= 1.5f / d1
            n1 * newFraction * newFraction + 0.75f
        } else if (newFraction < 2.5f / d1) {
            newFraction -= 2.25f / d1
            n1 * newFraction * newFraction + 0.9375f
        } else {
            newFraction -= 2.625f / d1
            n1 * newFraction * newFraction + 0.984375f
        }
    }
    val EaseOutQuart = CubicBezierEasing(0.25f, 1f, 0.5f, 1f)
}