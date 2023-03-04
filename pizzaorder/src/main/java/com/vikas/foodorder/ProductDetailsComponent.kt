package com.vikas.foodorder

import android.app.Activity
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.animateDp
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.animation.core.updateTransition
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.blur
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.calculateCurrentOffsetForPage
import com.google.accompanist.pager.rememberPagerState
import com.vikas.foodorder.theme.JetpackComposeAnimationPlaygroundTheme
import kotlin.math.absoluteValue

@OptIn(ExperimentalPagerApi::class)
@Composable
fun ProductDetailsComponent(list: List<PizzaDataModel> = PizzaDataModel.list) {
    val context = LocalContext.current
    val pagerState = rememberPagerState()
    var currentState by remember { mutableStateOf(ButtonState.Details) }
    val transition = updateTransition(currentState, label = "button state")
    val transitionAlpha by transition.animateFloat(
        transitionSpec = {
            tween(durationMillis = 500)
        }, label = ""
    ) {
        if (it == ButtonState.AddToCart) 1f else 0f
    }
    val transitionAlphaButton by transition.animateFloat(
        transitionSpec = {
            tween(durationMillis = 500)
        }, label = ""
    ) {
        if (it == ButtonState.Details) 1f else 0f
    }

    val transitionSize by transition.animateFloat(
        transitionSpec = {
            tween(durationMillis = 500)
        }, label = ""
    ) {
        if (it == ButtonState.AddToCart) {
            1f
        } else {
            0f
        }
    }

    val transitionSizeButton by transition.animateDp(
        transitionSpec = {
            tween(durationMillis = 500)
        }, label = ""
    ) {
        if (it == ButtonState.Details) {
            56.dp
        } else {
            0.dp
        }
    }

    var backgroundImage by remember { mutableStateOf(0) }

    val alphaAnim = animateFloatAsState(
        targetValue = Util.lerp(
            start = 0f, stop = 1f, fraction = 1f - pagerState.currentPageOffset.coerceIn(0f, 1f)
        ), animationSpec = tween(durationMillis = 700, easing = LinearOutSlowInEasing)
    )

    val offsetX: Float by animateFloatAsState(
        targetValue = Util.lerp(
            start = 400f, stop = 0f, fraction = 1f - pagerState.currentPageOffset.coerceIn(0f, 1f)
        ),

        animationSpec = tween(durationMillis = 500, easing = Util.EaseOutQuart)
    )

    LaunchedEffect(pagerState) {
        snapshotFlow { pagerState.currentPage }.collect { page ->
            backgroundImage = page
        }
    }

    Box(modifier = Modifier.fillMaxSize()) {
        Image(painter = painterResource(id = list[backgroundImage].pizzaImage),
            contentDescription = "Pizza",
            modifier = Modifier
                .size(400.dp)
                .offset(y = (-100).dp)
                .graphicsLayer {
                    translationX = offsetX
                }
                .alpha(alphaAnim.value)
                .blur(4.dp),
            contentScale = ContentScale.Crop)

        Image(
            painter = painterResource(id = R.drawable.veggie_101),
            contentDescription = "food background",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.BottomCenter)
                .offset(y = 150.dp),
        )

        Box(
            Modifier
                .background(color = Color.White.copy(alpha = .7f))
                .fillMaxSize()
        )

        Row(verticalAlignment = Alignment.CenterVertically) {
            Icon(
                painter = painterResource(id = R.drawable.ic_back),
                contentDescription = "close",
                modifier = Modifier
                    .padding(16.dp)
                    .size(32.dp)
                    .clickable {
                        (context as Activity).finish()
                    }
            )
            Spacer(modifier = Modifier.weight(1f))
            Icon(
                painter = painterResource(id = R.drawable.ic_cart),
                contentDescription = "cart",
                modifier = Modifier
                    .padding(16.dp)
                    .size(32.dp)
            )
        }

        when (currentState) {
            ButtonState.Details -> {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    HorizontalPager(
                        count = list.size,
                        state = pagerState,
                        modifier = Modifier.alpha(transitionAlphaButton)
                    ) { page ->
                        val pageOffset = calculateCurrentOffsetForPage(page).absoluteValue
                        ProductCarouselItem(list[page], pageOffset)
                    }

                    Button(
                        onClick = { currentState = ButtonState.AddToCart },
                        modifier = Modifier
                            .padding(horizontal = 24.dp)
                            .fillMaxWidth()
                            .height(transitionSizeButton)
                            .padding(horizontal = 16.dp),
                        shape = RoundedCornerShape(15),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color.Black, contentColor = Color.White
                        )
                    ) {
                        Text(text = "Add To Cart", modifier = Modifier.padding(8.dp))
                    }

                    Spacer(modifier = Modifier.height(16.dp))

                    Text(
                        text = "Swipe to see more",
                        color = Color.Gray.copy(alpha = .8f),
                        fontWeight = FontWeight.SemiBold,
                        modifier = Modifier.padding(horizontal = 16.dp)
                    )
                }
            }

            ButtonState.AddToCart -> {
                AddToCartComponent(list[backgroundImage], transitionAlpha, transitionSize) {
                    currentState = ButtonState.Details
                }
            }
        }
    }
}


@Preview(showSystemUi = true, showBackground = true)
@Composable
fun Preview_ProductDetailsComponent() {
    JetpackComposeAnimationPlaygroundTheme {
        ProductDetailsComponent()
    }
}