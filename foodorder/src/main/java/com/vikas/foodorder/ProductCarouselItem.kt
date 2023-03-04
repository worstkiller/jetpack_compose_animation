package com.vikas.foodorder

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.vikas.foodorder.theme.JetpackComposeAnimationPlaygroundTheme

@Composable
fun ProductCarouselItem(
    pizzaModel: PizzaDataModel, pageOffset: Float,
) {
    val angle = Util.lerp(
        start = 180f,
        stop = 0f,
        fraction = 1f - pageOffset.coerceIn(0f, 1f)
    )
    val imageAngle: Float by animateFloatAsState(
        targetValue = angle,

        animationSpec = tween(durationMillis = 600, easing = Util.EaseOutQuart)
    )
    val offsetX: Float by animateFloatAsState(
        targetValue = Util.lerp(
            start = 100f,
            stop = 0f,
            fraction = 1f - pageOffset.coerceIn(0f, 1f)
        ),

        animationSpec = tween(durationMillis = 400, easing = Util.EaseOutQuart)
    )
    val alphaDetails: Float by animateFloatAsState(
        targetValue = Util.lerp(
            start = 0f,
            stop = 1f,
            fraction = 1f - pageOffset.coerceIn(0f, 1f)
        ),

        animationSpec = tween(durationMillis = 600, easing = Util.EaseOutQuart)
    )

    val offsetY: Float by animateFloatAsState(
        targetValue = Util.lerp(
            start = 100f,
            stop = 0f,
            fraction = 1f - pageOffset.coerceIn(0f, 1f)
        ),

        animationSpec = tween(durationMillis = 600, easing = Util.EaseOutQuart)
    )

    val offsetDetailsX: Float by animateFloatAsState(
        targetValue = Util.lerp(
            start = 200f,
            stop = 0f,
            fraction = 1f - pageOffset.coerceIn(0f, 1f)
        ),

        animationSpec = tween(durationMillis = 800, easing = Util.EaseOutQuart)
    )

    Column(
        modifier = Modifier.wrapContentHeight(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Image(
            painter = painterResource(id = pizzaModel.pizzaImage),
            contentDescription = "Pizza",
            modifier = Modifier
                .size(240.dp)
                .graphicsLayer {
                    rotationY = imageAngle
                    translationX = offsetX
                    translationY = offsetY
                }
                .shadow(8.dp, shape = CircleShape, spotColor = Color(0xFFE91E63)),
            contentScale = ContentScale.Crop
        )
        Spacer(modifier = Modifier.height(52.dp))
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .alpha(alphaDetails)
                .offset(x = offsetDetailsX.dp)
        ) {
            Text(
                text = pizzaModel.pizzaName,
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                color = Color.DarkGray
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = pizzaModel.pizzaPrice,
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.SemiBold,
                color = Color(0xFFE91E63).copy(alpha = 0.7f),
                modifier = Modifier
                    .padding(horizontal = 16.dp)
            )
            Spacer(modifier = Modifier.height(30.dp))
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun Preview_ProductCarouselItem() {
    JetpackComposeAnimationPlaygroundTheme {
        ProductCarouselItem(PizzaDataModel.list.first(), 1f)
    }
}