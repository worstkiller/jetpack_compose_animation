package com.example.jetpackcomposeanimationplayground.ui

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.jetpackcomposeanimationplayground.R
import com.example.jetpackcomposeanimationplayground.ui.theme.Purple80
import com.example.jetpackcomposeanimationplayground.ui.theme.textColor
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.calculateCurrentOffsetForPage
import com.google.accompanist.pager.rememberPagerState
import kotlin.math.absoluteValue

@OptIn(ExperimentalPagerApi::class)
@Composable
fun HomeComponent() {
    val pagerState = rememberPagerState()
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {
        HorizontalPager(
            count = CarouselDataModel.listOfShoes.size,
            contentPadding = PaddingValues(start = 50.dp, end = 70.dp),
            state = pagerState
        ) { page ->
            val pageOffset = calculateCurrentOffsetForPage(page).absoluteValue
            ShoeItem(shoe = CarouselDataModel.listOfShoes[page], pageOffset)
        }
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .padding(start = 20.dp, end = 20.dp, bottom = 16.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Favorite",
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp,
                textAlign = TextAlign.Center,
                color = textColor
            )
            Image(
                painter = painterResource(id = R.drawable.ic_right_arrow),
                contentDescription = "more"
            )
        }
        TrendingShoes()
    }
}

@OptIn(ExperimentalPagerApi::class)
@Composable
fun TrendingShoes() {
    LazyRow(state = rememberLazyListState()) {
        items(TrendingProduct.list.size) { index ->
            TrendingProductItem(TrendingProduct.list[index])
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TrendingProductItem(product: TrendingProduct) {
    Card(
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        shape = RoundedCornerShape(16.dp),
        modifier = Modifier
            .width(180.dp)
            .height(190.dp)
            .padding(start = 16.dp, end = 8.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White)
    ) {
        Box {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {

                Spacer(modifier = Modifier.height(30.dp))

                Image(
                    painter = painterResource(id = product.image),
                    contentDescription = "shoe",
                    modifier = Modifier.padding(8.dp)
                )

                Spacer(modifier = Modifier.height(10.dp))

                Text(
                    text = product.name,
                    fontWeight = FontWeight.Medium,
                    fontSize = 16.sp,
                    textAlign = TextAlign.Center,
                    color = textColor
                )

                Spacer(modifier = Modifier.height(4.dp))

                Text(text = product.price, color = textColor)
            }

            Image(
                painter = painterResource(id = R.drawable.ic_heart),
                contentDescription = "fav",
                modifier = Modifier
                    .padding(12.dp)
                    .size(24.dp)
                    .align(Alignment.TopEnd)
                    .padding(2.dp)
            )

            Image(
                painter = painterResource(id = R.drawable.ic_ribbon),
                contentDescription = "fav",
                modifier = Modifier
                    .align(Alignment.TopStart)
                    .offset(x = 4.dp, y = (-4).dp)
                    .clip(RoundedCornerShape(8.dp)),
                colorFilter = ColorFilter.tint(Color(0xFFeb4658)),
                contentScale = ContentScale.Crop
            )
        }
    }
}

@Composable
fun ShoeItem(shoe: CarouselDataModel, pageOffset: Float) {
    val scale = Utils.lerp(
        start = 0.5f,
        stop = 1f,
        fraction = 1f - pageOffset.coerceIn(0f, 1f)
    )
    val angle = Utils.lerp(
        start = 30f,
        stop = 0f,
        fraction = 1f - pageOffset.coerceIn(0f, 1f)
    )
    val scaleXBox = Utils.lerp(
        start = 0.9f,
        stop = 1f,
        fraction = 1f - pageOffset.coerceIn(0f, 1f)
    )
    val scaleYBox = Utils.lerp(
        start = 0.7f,
        stop = 1f,
        fraction = 1f - pageOffset.coerceIn(0f, 1f)
    )
    val rotateY = Utils.lerp(
        start = 10f,
        stop = 0f,
        fraction = 1f - pageOffset.coerceIn(0f, 1f)
    )
    val boxAngle: Float by animateFloatAsState(
        targetValue = rotateY,
        // Configure the animation duration and easing.
        animationSpec = tween(durationMillis = 300, easing = Utils.EaseOutQuart)
    )
    val boxScaleX: Float by animateFloatAsState(
        targetValue = scaleXBox,
        // Configure the animation duration and easing.
        animationSpec = tween(durationMillis = 400, easing = Utils.EaseOutQuart)
    )
    val boxScaleY: Float by animateFloatAsState(
        targetValue = scaleYBox,
        // Configure the animation duration and easing.
        animationSpec = tween(durationMillis = 400, easing = Utils.EaseOutQuart)
    )
    val imageAngle: Float by animateFloatAsState(
        targetValue = angle,
        // Configure the animation duration and easing.
        animationSpec = tween(durationMillis = 300, easing = Utils.EaseOutQuart)
    )
    val visibility = Utils.lerp(
        start = 0f,
        stop = 1f,
        fraction = 1f - pageOffset.coerceIn(0f, 1f)
    )

    Box {
        Box(
            modifier = Modifier
                .graphicsLayer {
                    Utils
                        .lerp(
                            start = 0.90f,
                            stop = 1f,
                            fraction = 1f - pageOffset.coerceIn(0f, 1f)
                        )
                        .also { scale ->
                            scaleX = boxScaleX
                            scaleY = boxScaleY
                            rotationY = boxAngle
                        }
                }
                .height(280.dp)
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
                        color = Color.White.copy(alpha = .9f),
                        fontWeight = FontWeight.Light
                    )
                }

                Spacer(modifier = Modifier.weight(1f))

                Image(
                    modifier = Modifier.size(24.dp),
                    painter = painterResource(id = R.drawable.ic_search),
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

@Composable
@Preview(showBackground = true, showSystemUi = true)
fun Preview_HomeComponent() {
    HomeComponent()
}