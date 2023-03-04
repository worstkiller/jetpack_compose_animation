package com.vikas.composeapp.ui.dashboard

import android.widget.Toast
import androidx.compose.animation.core.*
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.vikas.foodorder.FoodAppMainActivity
import com.vikas.shoeapp.MainActivity

@Composable
fun DashboardComponent() {
    val context = LocalContext.current

    Column(modifier = Modifier) {
        LazyColumn(modifier = Modifier.fillMaxSize()) {
            items(DashboardOptions.values()) {
                ProjectItem(it) {
                    when (it) {
                        DashboardOptions.SHOE_APP -> {
                            context.startActivity(MainActivity.getIntent(context))
                        }
                        DashboardOptions.FOOD_ORDER_APP -> {
                            context.startActivity(FoodAppMainActivity.getIntent(context))
                        }
                        else -> {
                            Toast.makeText(context, "Coming Soon", Toast.LENGTH_SHORT).show()
                        }
                    }

                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProjectItem(project: DashboardOptions, onClick: (DashboardOptions) -> Unit) {
    Card(
        onClick = { onClick(project) },
        modifier = Modifier.padding(16.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
    ) {

        Box {
            Image(
                painter = painterResource(id = project.image),
                contentDescription = "project",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(180.dp),
                contentScale = ContentScale.Crop,
            )
            Row(
                modifier = Modifier
                    .alpha(.9f)
                    .fillMaxWidth()
                    .background(
                        brush = Brush.horizontalGradient(
                            listOf(
                                Color(0xFFFF5F6D),
                                Color(0xFFFFC371)
                            )
                        )
                    )
                    .padding(16.dp)
                    .align(Alignment.BottomStart),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = project.title,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Medium,
                    color = Color.White
                )
                Icon(
                    imageVector = Icons.Default.ArrowForward,
                    contentDescription = "Arrow Forward",
                    tint = Color.White
                )
            }
        }

    }
}

@Composable
@Preview(showBackground = true, showSystemUi = true)
fun Preview_DashboardComponent() {
    DashboardComponent()
}
