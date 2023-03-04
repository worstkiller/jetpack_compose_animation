package com.vikas.foodorder

import androidx.compose.animation.core.CubicBezierEasing

object Util {

    fun lerp(start: Float, stop: Float, fraction: Float): Float =
        (1 - fraction) * start + fraction * stop

    val EaseOutQuart = CubicBezierEasing(0.25f, 1f, 0.5f, 1f)

}