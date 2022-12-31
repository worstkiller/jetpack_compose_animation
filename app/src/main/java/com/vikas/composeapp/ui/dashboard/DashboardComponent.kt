package com.vikas.composeapp.ui.dashboard

import androidx.compose.animation.core.*
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.ColorMatrix
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.vikas.composeapp.R
import com.vikas.shoeapp.MainActivity
import com.vikas.shoeapp.ui.theme.textColor

@Composable
fun DashboardComponent(modifier: Modifier, onMenuClick: () -> Unit) {
    val context = LocalContext.current

    Column(modifier = modifier) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_menu),
                contentDescription = "Menu",
                modifier = Modifier
                    .size(24.dp)
                    .clickable { onMenuClick() },
                colorFilter = ColorFilter.tint(textColor)
            )

            Spacer(modifier = Modifier.width(16.dp))

            Text(
                text = "Projects",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                color = textColor
            )
        }

        Box(
            modifier = Modifier
                .height(1.dp)
                .fillMaxWidth()
                .background(Color.LightGray)
        )

        LazyColumn(modifier = Modifier.fillMaxSize()) {
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProjectItem(project: AppProject, onClick: (AppProject) -> Unit) {
    Card(
        onClick = { onClick(project) },
        modifier = Modifier.padding(16.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 12.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
    ) {

        Image(
            painter = painterResource(id = project.image),
            contentDescription = "project",
            modifier = Modifier
                .fillMaxWidth()
                .height(120.dp),
            contentScale = ContentScale.Crop,
        )

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    brush = Brush.horizontalGradient(
                        listOf(
                            Color(0xFFFF5F6D),
                            Color(0xFFFFC371)
                        )
                    )
                )
                .padding(24.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = project.title,
                fontSize = 24.sp,
                fontWeight = FontWeight.Medium,
                color = textColor
            )
            Icon(
                imageVector = Icons.Default.ArrowForward,
                contentDescription = "Arrow Forward",
                tint = textColor
            )
        }
    }
}

@Composable
@Preview(showBackground = true, showSystemUi = true)
fun Preview_DashboardComponent() {
    DashboardComponent(Modifier.background(Color(0xFFebf2fa))) {}
}
