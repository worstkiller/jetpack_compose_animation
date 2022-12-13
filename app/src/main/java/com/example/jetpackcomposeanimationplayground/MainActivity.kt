package com.example.jetpackcomposeanimationplayground

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.core.*
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.jetpackcomposeanimationplayground.ui.CarouselDataModel
import com.example.jetpackcomposeanimationplayground.ui.Utils.EaseOutQuart
import com.example.jetpackcomposeanimationplayground.ui.Utils.lerp
import com.example.jetpackcomposeanimationplayground.ui.theme.JetpackComposeAnimationPlaygroundTheme
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.calculateCurrentOffsetForPage
import com.google.accompanist.pager.rememberPagerState
import kotlin.math.absoluteValue

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JetpackComposeAnimationPlaygroundTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    HomeComponent()
                }
            }
        }
    }
}

@OptIn(ExperimentalPagerApi::class)
@Composable
fun HomeComponent() {
    val pagerState = rememberPagerState()
    HorizontalPager(
        count = CarouselDataModel.listOfShoes.size,
        contentPadding = PaddingValues(start = 50.dp, end = 70.dp),
        state = pagerState
    ) { page ->
        val pageOffset = calculateCurrentOffsetForPage(page).absoluteValue
        ShoeItem(shoe = CarouselDataModel.listOfShoes[page], pageOffset)
    }
}

@Composable
fun ShoeItem(shoe: CarouselDataModel, pageOffset: Float) {
    val scale = lerp(
        start = 0.5f,
        stop = 1f,
        fraction = 1f - pageOffset.coerceIn(0f, 1f)
    )
    val angle = lerp(
        start = 30f,
        stop = 0f,
        fraction = 1f - pageOffset.coerceIn(0f, 1f)
    )
    val scaleXBox = lerp(
        start = 0.9f,
        stop = 1f,
        fraction = 1f - pageOffset.coerceIn(0f, 1f)
    )
    val scaleYBox = lerp(
        start = 0.7f,
        stop = 1f,
        fraction = 1f - pageOffset.coerceIn(0f, 1f)
    )
    val rotateY = lerp(
        start = 10f,
        stop = 0f,
        fraction = 1f - pageOffset.coerceIn(0f, 1f)
    )
    val boxAngle: Float by animateFloatAsState(
        targetValue = rotateY,
        // Configure the animation duration and easing.
        animationSpec = tween(durationMillis = 300, easing = EaseOutQuart)
    )
    val boxScaleX: Float by animateFloatAsState(
        targetValue = scaleXBox,
        // Configure the animation duration and easing.
        animationSpec = tween(durationMillis = 400, easing = EaseOutQuart)
    )
    val boxScaleY: Float by animateFloatAsState(
        targetValue = scaleYBox,
        // Configure the animation duration and easing.
        animationSpec = tween(durationMillis = 400, easing = EaseOutQuart)
    )
    val imageAngle: Float by animateFloatAsState(
        targetValue = angle,
        // Configure the animation duration and easing.
        animationSpec = tween(durationMillis = 300, easing = EaseOutQuart)
    )
    val visibility = lerp(
        start = 0f,
        stop = 1f,
        fraction = 1f - pageOffset.coerceIn(0f, 1f)
    )

    Box {
        Box(
            modifier = Modifier
                .graphicsLayer {
                    lerp(
                        start = 0.90f,
                        stop = 1f,
                        fraction = 1f - pageOffset.coerceIn(0f, 1f)
                    ).also { scale ->
                        scaleX = boxScaleX
                        scaleY = boxScaleY
                        rotationY = boxAngle
                    }
                }
                .height(300.dp)
                .width(210.dp)
                .background(color = shoe.color.copy(alpha = .8f), RoundedCornerShape(20.dp))
                .padding(end = 16.dp)
        ) {
            Row(
                modifier = Modifier
                    .padding(top = 16.dp, start = 16.dp)
                    .alpha(visibility)
            ) {
                Column {
                    Text(
                        text = shoe.title,
                        fontSize = 16.sp,
                        color = Color.White,
                        fontWeight = FontWeight.Medium
                    )
                    Text(
                        text = shoe.description,
                        color = Color.White,
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        text = shoe.price,
                        fontSize = 14.sp,
                        color = Color.White.copy(alpha = .8f),
                        fontWeight = FontWeight.Light
                    )
                }

                Spacer(modifier = Modifier.weight(1f))

                Image(
                    modifier = Modifier.size(24.dp),
                    painter = painterResource(id = R.drawable.ic_heart),
                    contentDescription = "like",
                    colorFilter = ColorFilter.tint(Color.White),
                )
            }
            Image(
                modifier = Modifier
                    .padding(bottom = 16.dp)
                    .size(24.dp)
                    .align(Alignment.BottomEnd),
                painter = painterResource(id = R.drawable.ic_right_arrow),
                contentDescription = "go to next",
                colorFilter = ColorFilter.tint(Color.White),
            )
        }
        Box(
            modifier = Modifier
                .height(300.dp)
                .width(220.dp)
        ) {

            Image(
                painter = painterResource(id = shoe.resId),
                contentDescription = "",
                modifier = Modifier
                    .height(340.dp)
                    .align(
                        Alignment.Center
                    )
                    .rotate(330f)
                    .offset(x = 20.dp, y = 10.dp)
                    .size(320.dp)
                    .graphicsLayer {
                        scaleX = scale
                        scaleY = scale
                        rotationZ = imageAngle
                    },
                contentScale = ContentScale.Fit
            )
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun DefaultPreview() {
    JetpackComposeAnimationPlaygroundTheme {
        HomeComponent()
    }
}