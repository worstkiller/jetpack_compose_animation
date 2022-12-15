package com.example.jetpackcomposeanimationplayground

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.jetpackcomposeanimationplayground.ui.BottomNav
import com.example.jetpackcomposeanimationplayground.ui.HomeComponent
import com.example.jetpackcomposeanimationplayground.ui.theme.*

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JetpackComposeAnimationPlaygroundTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    DashboardComponent()
                }
            }
        }
    }
}

@Composable
fun DashboardComponent() {
    val screen = remember { mutableStateOf(BottomNav.Home) }
    Column(
        modifier = Modifier.fillMaxSize()
    ) {

        HomeToolbar()

        Column(
            modifier = Modifier.weight(1f),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            when (screen.value) {
                BottomNav.Home -> HomeComponent()
                else -> {
                    Text(
                        text = "Coming Soon",
                        fontWeight = FontWeight.Bold,
                        fontSize = 32.sp,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.fillMaxWidth(),
                        color = textColor
                    )
                }
            }
        }

        BottomToolbar(screen)
    }
}

@Composable
fun BottomToolbar(screen: MutableState<BottomNav>) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalArrangement = Arrangement.SpaceAround
    ) {
        BottomNav.values().forEach { nav ->
            Image(
                painter = painterResource(id = nav.icon),
                contentDescription = "search",
                modifier = Modifier
                    .padding(4.dp)
                    .size(40.dp)
                    .padding(6.dp)
                    .clickable {
                        screen.value = nav
                    },
                colorFilter = if (screen.value == nav) {
                    ColorFilter.tint(Purple40)
                } else {
                    ColorFilter.tint(PurpleGrey40.copy(alpha = 0.5f))
                }
            )
        }
    }
}

@Composable
fun HomeToolbar() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(20.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = "Discover",
            fontWeight = FontWeight.Bold,
            fontSize = 30.sp,
            color = textColor
        )

        Spacer(modifier = Modifier.weight(1f))

        Image(
            painter = painterResource(id = R.drawable.ic_search),
            contentDescription = "search",
            modifier = Modifier
                .padding(4.dp)
                .size(36.dp)
                .background(color = Color.Gray.copy(alpha = .2f), shape = CircleShape)
                .padding(6.dp)
        )

        Image(
            painter = painterResource(id = R.drawable.ic_notification),
            contentDescription = "search",
            modifier = Modifier
                .padding(4.dp)
                .size(36.dp)
                .background(color = Color.Gray.copy(alpha = .2f), shape = CircleShape)
                .padding(6.dp)
        )

    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun DefaultPreview() {
    JetpackComposeAnimationPlaygroundTheme {
        DashboardComponent()
    }
}