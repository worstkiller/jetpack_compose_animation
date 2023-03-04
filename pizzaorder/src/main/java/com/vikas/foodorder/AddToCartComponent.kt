package com.vikas.foodorder

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.ColorMatrix
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.vikas.foodorder.theme.JetpackComposeAnimationPlaygroundTheme

@Composable
fun AddToCartComponent(
    pizzaModel: PizzaDataModel,
    transitionAlpha: Float,
    onClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .alpha(transitionAlpha)
    ) {

        Text(
            text = "Back to Details",
            color = Color.Gray,
            fontWeight = FontWeight.SemiBold,
            textDecoration = TextDecoration.Underline,
            modifier = Modifier
                .clickable {
                    onClick()
                }
                .align(Alignment.BottomCenter)
                .padding(bottom = 50.dp),
        )

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(.70f)
                .padding(40.dp)
                .background(shape = RoundedCornerShape(20.dp), color = Color.Black)
                .align(alignment = Alignment.Center)
                .clip(RoundedCornerShape(20.dp))
        ) {

            Text(
                text = "Amount",
                style = MaterialTheme.typography.h5,
                color = Color.White,
                fontWeight = FontWeight.SemiBold,
                fontSize = 28.sp,
                modifier = Modifier.padding(top = 30.dp)
            )

            Text(
                text = pizzaModel.pizzaPrice,
                fontSize = 20.sp,
                color = Color(0xFFE91E63),
                modifier = Modifier.padding(top = 16.dp)
            )

            Spacer(modifier = Modifier.height(20.dp))

            Image(
                painter = painterResource(id = R.drawable.ic_verified),
                contentDescription = "check",
                colorFilter = ColorFilter.tint(Color.Green.copy(alpha = .8f)),
                modifier = Modifier.size(90.dp)
            )

            Image(
                painter = painterResource(id = pizzaModel.pizzaImage),
                contentDescription = "food image",
                contentScale = ContentScale.FillHeight,
                modifier = Modifier
                    .fillMaxWidth()
                    .size(400.dp)
                    .offset(y = 60.dp),
            )

        }
    }
}

@Preview(showSystemUi = true, showBackground = true)
@Composable
fun Preview_AddToCartComponent() {
    JetpackComposeAnimationPlaygroundTheme {
        AddToCartComponent(PizzaDataModel.list.first(), 1f) {

        }
    }
}