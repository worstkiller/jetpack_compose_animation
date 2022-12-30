package com.vikas.shoeapp.ui.home

import android.app.Activity
import androidx.compose.animation.animateColor
import androidx.compose.animation.core.*
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.vikas.shoeapp.R
import com.vikas.shoeapp.data.CarouselDataModel
import com.vikas.shoeapp.ui.cart.CartActivity
import com.vikas.shoeapp.ui.theme.accentColor
import com.vikas.shoeapp.ui.theme.textColor

enum class DetailState {
    COLLAPSED,
    EXPANDED
}

enum class ButtonState {
    DEFAULT,
    LOADING,
    LOADED
}

@Composable
fun DetailComponent(
    carouselDataModel: CarouselDataModel = CarouselDataModel.listOfShoes.first(),
    viewModel: MainViewModel
) {
    var currentState by remember { mutableStateOf(DetailState.COLLAPSED) }
    val buttonState by remember { viewModel.buttonState }

    val transition = updateTransition(currentState, label = "")
    val context = LocalContext.current

    LaunchedEffect(key1 = currentState) {
        currentState = DetailState.EXPANDED
        val activity = context as Activity
        activity.window.statusBarColor = carouselDataModel.color.toArgb()

        viewModel.cartFlow.collect {
            if (it) {
                context.startActivity(CartActivity.intent(context))
            }
        }
    }

    val size by transition.animateDp(
        transitionSpec = {
            if (DetailState.COLLAPSED isTransitioningTo DetailState.EXPANDED) {
                spring(stiffness = 100f, dampingRatio = 0.5f)
            } else {
                tween(durationMillis = 500)
            }
        },
        label = ""
    ) {
        if (it == DetailState.COLLAPSED) {
            0.dp
        } else {
            300.dp
        }
    }

    val circleSize by transition.animateFloat(
        transitionSpec = {
            if (DetailState.COLLAPSED isTransitioningTo DetailState.EXPANDED) {
                spring(stiffness = 100f, dampingRatio = 0.5f)
            } else {
                tween(durationMillis = 500)
            }
        },
        label = ""
    ) {
        if (it == DetailState.COLLAPSED) {
            0f
        } else {
            1.6f
        }
    }

    val color by transition.animateColor(
        transitionSpec = {
            if (DetailState.COLLAPSED isTransitioningTo DetailState.EXPANDED) {
                tween(durationMillis = 500)
            } else {
                tween(durationMillis = 500)
            }
        },
        label = ""
    ) {
        if (it == DetailState.COLLAPSED) {
            Color.Transparent
        } else {
            carouselDataModel.color
        }
    }

    val translateY by transition.animateOffset(
        transitionSpec = {
            if (DetailState.COLLAPSED isTransitioningTo DetailState.EXPANDED) {
                tween(durationMillis = 500)
            } else {
                tween(durationMillis = 500)
            }
        },
        label = ""
    ) {
        if (it == DetailState.COLLAPSED) {
            Offset(0f, 100f)
        } else {
            Offset(0f, 0f)
        }
    }

    val alpha by transition.animateFloat(
        transitionSpec = {
            if (DetailState.COLLAPSED isTransitioningTo DetailState.EXPANDED) {
                tween(durationMillis = 500)
            } else {
                tween(durationMillis = 500)
            }
        },
        label = ""
    ) {
        if (it == DetailState.COLLAPSED) {
            0f
        } else {
            1f
        }
    }

    Box {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(420.dp)
            ) {
                Box(modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight()
                    .graphicsLayer {
                        translationX = 180f
                        translationY = -350f
                        scaleX = circleSize
                    }
                    .background(shape = CircleShape, color = color))

                DetailsToolbar(carouselDataModel, viewModel)

                Image(
                    painter = painterResource(id = carouselDataModel.resId),
                    contentDescription = "image",
                    modifier = Modifier
                        .padding(bottom = 20.dp)
                        .align(Alignment.Center)
                        .rotate(330f)
                        .size(size)
                )

                LazyRow(
                    modifier = Modifier
                        .align(Alignment.BottomCenter)
                        .padding(bottom = 8.dp)
                        .alpha(alpha)
                ) {
                    items(4) { _ ->
                        Image(
                            painter = painterResource(id = carouselDataModel.resId),
                            contentDescription = "images",
                            modifier = Modifier
                                .padding(8.dp)
                                .background(
                                    color = Color.LightGray.copy(.2f),
                                    shape = RoundedCornerShape(4.dp)
                                )
                                .width(80.dp)
                                .height(60.dp)
                                .padding(8.dp)
                        )
                    }
                }
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .alpha(alpha)
                        .height(1.dp)
                        .background(Color.LightGray)
                        .align(Alignment.BottomCenter)

                )
            }
            Column(
                modifier = Modifier
                    .offset(translateY.x.dp, translateY.y.dp)
                    .alpha(alpha)
            ) {
                Row(
                    Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = carouselDataModel.description,
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold,
                        color = textColor
                    )
                    Text(
                        text = carouselDataModel.price,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        color = textColor,
                        modifier = Modifier
                            .background(
                                carouselDataModel.color.copy(alpha = .1f),
                                shape = RoundedCornerShape(8.dp)
                            )
                            .padding(4.dp)
                    )
                }
                Text(
                    text = carouselDataModel.aboutProduct,
                    color = textColor.copy(alpha = .5f),
                    modifier = Modifier.padding(start = 16.dp, end = 16.dp),
                    fontSize = 16.sp,
                    maxLines = 3,
                    overflow = TextOverflow.Ellipsis
                )
                Text(
                    text = "MORE DETAILS",
                    color = textColor,
                    modifier = Modifier.padding(16.dp),
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Medium,
                    textDecoration = TextDecoration.Underline
                )

                Row(modifier = Modifier.padding(start = 16.dp, end = 16.dp)) {
                    Text(
                        text = "Size",
                        color = textColor,
                        fontSize = 22.sp,
                        fontWeight = FontWeight.Bold,
                    )
                    Spacer(modifier = Modifier.weight(1f))
                    Text(
                        text = "UK",
                        color = textColor,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Medium,
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = "USA",
                        color = textColor.copy(alpha = .5f),
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Medium,
                    )
                }
                LazyRow {
                    items(4) { _ ->
                        Box(
                            modifier = Modifier
                                .padding(16.dp)
                                .background(
                                    color = Color.LightGray.copy(.2f),
                                    shape = RoundedCornerShape(4.dp)
                                )
                                .width(60.dp)
                                .height(50.dp)
                                .padding(4.dp)
                        ) {
                            Text(
                                text = "8",
                                color = textColor,
                                fontSize = 18.sp,
                                fontWeight = FontWeight.Medium,
                                modifier = Modifier.align(Alignment.Center)
                            )
                        }
                    }
                }
                Spacer(modifier = Modifier.height(60.dp))
            }
        }
        Button(
            onClick = {
                viewModel.changeButtonState()
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp)
                .padding(start = 16.dp, end = 16.dp, bottom = 8.dp)
                .align(Alignment.BottomCenter)
                .alpha(alpha),
            shape = RoundedCornerShape(8.dp),
            colors = ButtonDefaults.buttonColors(containerColor = carouselDataModel.color)
        ) {
            if (buttonState == ButtonState.DEFAULT) {
                Text(text = "ADD TO BAG", modifier = Modifier.padding(8.dp))
            } else if (buttonState == ButtonState.LOADING) {
                CircularProgressIndicator(
                    color = Color.White.copy(alpha = 0.8f),
                    modifier = Modifier.size(30.dp)
                )
            } else {
                Text(text = "✓ Go to cart", modifier = Modifier.padding(8.dp))
            }
        }
    }
}

@Composable
fun DetailsToolbar(carouselDataModel: CarouselDataModel, viewModel: MainViewModel) {
    val context = LocalContext.current
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_right_arrow),
            contentDescription = "back",
            modifier = Modifier
                .rotate(180f)
                .size(24.dp)
                .clickable {
                    val activity = context as Activity
                    activity.window.statusBarColor = accentColor.toArgb()
                    viewModel.onBackClick()
                },
            colorFilter = ColorFilter.tint(Color.White)
        )

        Text(
            text = carouselDataModel.title,
            fontSize = 18.sp,
            fontWeight = FontWeight.Medium,
            color = Color.White
        )

        Image(
            painter = painterResource(id = R.drawable.ic_heart),
            contentDescription = "back",
            modifier = Modifier.size(24.dp),
            colorFilter = ColorFilter.tint(Color.White)
        )
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun Preview_DetailComponent() {
    DetailComponent(viewModel = MainViewModel())
}