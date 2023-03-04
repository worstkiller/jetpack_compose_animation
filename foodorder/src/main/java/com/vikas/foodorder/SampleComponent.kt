package com.vikas.foodorder

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.animation.core.*
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.*
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import java.time.Duration

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun CircleOnCurve() {
    var progress by remember { mutableStateOf(0f) }

//    val animationSpec = infiniteRepeatable(
//        animation = tween(
//            durationMillis = 2000,
//            easing = LinearEasing
//        )
//    )

//    LaunchedEffect(Unit) {
//        while (true) {
//            withFrameNanos { nanoTime ->
//                progress = animationSpec.animateTo(
//                    targetValue = 1f,
//                    initialVelocity = 0f,
//                    frameTimeMillis = Duration.ofNanos(nanoTime).toMillis()
//                ).value
//            }
//        }
//    }

    Box(modifier = Modifier.fillMaxSize()) {
        Canvas(modifier = Modifier.fillMaxSize()) {
            val path = Path().apply {
                moveTo(size.width / 4, size.height / 4)
                quadraticBezierTo(
                    0f,
                    400f,
                    size.width,
                   400f,
                )
            }
            drawPath(path, brush = SolidColor(Color.LightGray), style = Stroke(width = 8.dp.toPx()))

//            val circlePosition = pathMeasure(path, false, progress)

//            drawCircle(
//                color = Color.Red,
//                radius = 20.dp.toPx(),
//                center = Offset(circlePosition.x, circlePosition.y)
//            )
        }
    }
}


//private fun pathMeasure(path: Path, forceClosed: Boolean, progress: Float): Offset {
//    val pathMeasure = PathMeasure(path, forceClosed)
//    val pos = FloatArray(2)
//    val tan = FloatArray(2)
//    pathMeasure.getPosTan(pathMeasure.length * progress, pos, tan)
//    return Offset(pos[0], pos[1])
//}
