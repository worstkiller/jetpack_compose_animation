package com.vikas.composeapp.ui.dashboard

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.vikas.composeapp.util.Util

@Composable
fun DashboardComponent() {
    val context = LocalContext.current
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.LightGray)
    ) {
        LazyColumn {
            items(AppProject.values()) {
                ProjectItem(it) {
                    val intent = when (it) {
                        AppProject.SHOE_APP -> MainActivity.getIntent(context)
                        else -> {
                            MainActivity.getIntent(context)
                        }
                    }
                    context.startActivity(intent)
                }
            }
        }

    }
}

@Composable
fun ProjectItem(project: AppProject, onClick: (AppProject) -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .background(
                brush = Brush.horizontalGradient(
                    listOf(
                        Util.randomColor(),
                        Util.randomColor()
                    )
                ), shape = RoundedCornerShape(20.dp)
            )
            .shadow(10.dp)
            .clip(RoundedCornerShape(20.dp))
            .padding(16.dp)
            .height(58.dp)
            .clickable {
                onClick(project)
            },
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = project.title,
            fontSize = 24.sp,
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

@Composable
@Preview(showBackground = true, showSystemUi = true)
fun Preview_DashboardComponent() {
    DashboardComponent()
}
