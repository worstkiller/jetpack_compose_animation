package com.vikas.composeapp.util

import androidx.compose.ui.graphics.Color

object Util {

    fun randomColor(): Color {
        return listOf(
            0xFFE57373.toInt(),
            0xFFF06292.toInt(),
            0xFFBA68C8.toInt(),
            0xFF9575CD.toInt(),
            0xFF7986CB.toInt(),
            0xFF64B5F6.toInt(),
            0xFF4FC3F7.toInt(),
            0xFF4DD0E1.toInt(),
            0xFF4DB6AC.toInt(),
            0xFF81C784.toInt(),
            0xFFAED581.toInt(),
            0xFFDCE775.toInt(),
            0xFFFFD54F.toInt(),
            0xFFFFB74D.toInt(),
            0xFFA1887F.toInt(),
            0xFF90A4AE.toInt()
        ).map {
            Color(it)
        }.random()
    }

}