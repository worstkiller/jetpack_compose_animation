package com.vikas.composeapp.ui.home

import androidx.compose.animation.core.*
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.capitalize
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.vikas.composeapp.BuildConfig
import com.vikas.composeapp.MenuState
import com.vikas.composeapp.R
import com.vikas.composeapp.ui.cart.CartComponent
import com.vikas.composeapp.ui.dashboard.DashboardComponent
import com.vikas.composeapp.ui.favorite.FavoriteComponent
import com.vikas.composeapp.ui.profile.ProfileComponent
import com.vikas.composeapp.ui.settings.SettingsComponent
import com.vikas.shoeapp.ui.theme.textColor
import java.util.*
import kotlin.math.roundToInt

@Composable
fun HomeComponent() {
    var screen by remember { mutableStateOf(HomeMenu.HOME.name) }
    var currentState by remember { mutableStateOf(MenuState.COLLAPSED) }
    val updateAnim = updateTransition(currentState, label = "MenuState")
    val scale = updateAnim.animateFloat(
        transitionSpec = {
            when {
                MenuState.EXPANDED isTransitioningTo MenuState.COLLAPSED -> {
                    tween(
                        durationMillis = 300,
                        easing = LinearOutSlowInEasing
                    )
                }
                MenuState.COLLAPSED isTransitioningTo MenuState.EXPANDED -> {
                    tween(
                        durationMillis = 300,
                        easing = LinearOutSlowInEasing
                    )
                }
                else -> {
                    snap()
                }
            }
        }, label = ""
    ) {
        when (it) {
            MenuState.EXPANDED -> 0.7f
            MenuState.COLLAPSED -> 1f
        }
    }
    val transitionOffset = updateAnim.animateOffset({
        when {
            MenuState.EXPANDED isTransitioningTo MenuState.COLLAPSED -> {
                tween(
                    durationMillis = 300,
                    easing = LinearOutSlowInEasing
                )
            }
            MenuState.COLLAPSED isTransitioningTo MenuState.EXPANDED -> {
                tween(
                    durationMillis = 300,
                    easing = LinearOutSlowInEasing
                )
            }
            else -> {
                snap()
            }
        }
    }, label = "") {
        when (it) {
            MenuState.EXPANDED -> Offset(750f, 60f)
            MenuState.COLLAPSED -> Offset(0f, 0f)
        }
    }

    val alphaMenu = updateAnim.animateFloat({
        when {
            MenuState.EXPANDED isTransitioningTo MenuState.COLLAPSED -> {
                tween(durationMillis = 300)
            }
            MenuState.COLLAPSED isTransitioningTo MenuState.EXPANDED -> {
                tween(durationMillis = 300)
            }
            else -> {
                snap()
            }
        }
    }, label = "") {
        when (it) {
            MenuState.EXPANDED -> 1f
            MenuState.COLLAPSED -> 0.5f
        }
    }

    val roundness = updateAnim.animateDp({
        when {
            MenuState.EXPANDED isTransitioningTo MenuState.COLLAPSED -> {
                tween(durationMillis = 300)
            }
            MenuState.COLLAPSED isTransitioningTo MenuState.EXPANDED -> {
                tween(durationMillis = 300)
            }
            else -> {
                snap()
            }
        }
    }, label = "") {
        when (it) {
            MenuState.EXPANDED -> 20.dp
            MenuState.COLLAPSED -> 0.dp
        }
    }

    val menuOffset = updateAnim.animateOffset({
        when {
            MenuState.EXPANDED isTransitioningTo MenuState.COLLAPSED -> {
                tween(
                    durationMillis = 300,
                    easing = LinearOutSlowInEasing
                )
            }
            MenuState.COLLAPSED isTransitioningTo MenuState.EXPANDED -> {
                tween(
                    durationMillis = 300,
                    easing = LinearOutSlowInEasing
                )
            }
            else -> {
                snap()
            }
        }
    }, label = "") {
        when (it) {
            MenuState.EXPANDED -> Offset(0f, 0f)
            MenuState.COLLAPSED -> Offset(-100f, 0f)
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF064789))
    ) {

        //side menu
        MenuComponent(
            Modifier
                .offset {
                    IntOffset(
                        menuOffset.value.x.roundToInt(),
                        menuOffset.value.y.roundToInt()
                    )
                }
                .alpha(alphaMenu.value),
        ) {
            when (it) {
                is HomeMenuAction.MenuSelected -> {
                    screen = it.menu.name
                }
                HomeMenuAction.SETTINGS -> {
                    screen = "SETTINGS"
                }
                HomeMenuAction.LOGOUT -> {
                    //do logout
                }
                else -> {
                    currentState = MenuState.COLLAPSED
                }
            }
            currentState = MenuState.COLLAPSED
        }

        // stack layer 0
        Box(
            modifier = Modifier
                .fillMaxSize()
                .scale(scale.value - 0.05f)
                .offset {
                    IntOffset(
                        transitionOffset.value.x.toInt() - 50,
                        transitionOffset.value.y.toInt()
                    )
                }
                .background(Color(0xFFF3F6FA).copy(alpha = .90f), shape = RoundedCornerShape(20.dp))
                .padding(8.dp)
                .alpha(alphaMenu.value)
        )
        //stack layer 1
        Box(
            modifier = Modifier
                .fillMaxSize()
                .scale(scale.value - 0.08f)
                .offset {
                    IntOffset(
                        transitionOffset.value.x.toInt() - 100,
                        transitionOffset.value.y.toInt()
                    )
                }
                .background(Color(0xFFF3F6FA).copy(.5f), shape = RoundedCornerShape(20.dp))
                .padding(8.dp)
                .alpha(alphaMenu.value)
        )
        // dashboard content
        Column(modifier = Modifier
            .fillMaxSize()
            .scale(scale.value)
            .offset {
                IntOffset(
                    transitionOffset.value.x.toInt(),
                    transitionOffset.value.y.toInt()
                )
            }
            .clip(shape = RoundedCornerShape(roundness.value))
            .background(color = Color(0xFFebf2fa))) {
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
                        .clickable {
                            currentState = when (currentState) {
                                MenuState.EXPANDED -> MenuState.COLLAPSED
                                MenuState.COLLAPSED -> MenuState.EXPANDED
                            }
                        },
                    colorFilter = ColorFilter.tint(textColor)
                )

                Spacer(modifier = Modifier.width(16.dp))

                Text(
                    text = screen,
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
            when (screen) {
                HomeMenu.HOME.name -> {
                    DashboardComponent()
                }
                HomeMenu.PROFILE.name -> {
                    ProfileComponent()
                }
                HomeMenu.CART.name -> {
                    CartComponent()
                }
                HomeMenu.FAVORITE.name -> {
                    FavoriteComponent()
                }
                "SETTINGS" -> {
                    SettingsComponent()
                }
            }
        }

    }
}

@Composable
fun MenuComponent(modifier: Modifier, menuAction: (HomeMenuAction) -> Unit) {

    Column(modifier = modifier.padding(16.dp), verticalArrangement = Arrangement.Center) {

        Spacer(modifier = Modifier.height(40.dp))

        Row(verticalAlignment = Alignment.CenterVertically) {
            Image(
                painter = painterResource(id = R.drawable.profile_image),
                contentDescription = "profile pic",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(50.dp)
                    .clip(shape = CircleShape)
            )

            Text(
                text = "Sara",
                fontStyle = MaterialTheme.typography.titleMedium.fontStyle,
                color = Color.White,
                fontSize = 24.sp,
                modifier = Modifier.padding(start = 16.dp)
            )

            Spacer(modifier = Modifier.weight(1f))
        }

        Spacer(modifier = Modifier.weight(1f))

        LazyColumn {

            items(HomeMenu.values()) {

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .padding(start = 16.dp, end = 16.dp, top = 26.dp, bottom = 16.dp)
                        .clickable {
                            menuAction(HomeMenuAction.MenuSelected(it))
                        }
                ) {
                    Icon(
                        painter = painterResource(id = it.icon),
                        contentDescription = it.title,
                        tint = Color.White,
                        modifier = Modifier.size(24.dp)
                    )

                    Spacer(modifier = Modifier.width(16.dp))

                    Text(
                        text = it.title,
                        color = Color.White,
                        fontSize = 16.sp,
                        modifier = Modifier.padding(start = 16.dp),
                        fontWeight = FontWeight.Medium
                    )
                }

            }
        }

        Spacer(modifier = Modifier.weight(1f))

        //settings
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .padding(start = 16.dp, end = 16.dp, top = 16.dp, bottom = 16.dp)
                .clickable {
                    menuAction(HomeMenuAction.SETTINGS)
                }
        ) {
            Icon(
                painter = painterResource(id = com.vikas.shoeapp.R.drawable.ic_settings),
                contentDescription = "Settings",
                tint = Color.White,
                modifier = Modifier.size(24.dp)
            )

            Spacer(modifier = Modifier.width(16.dp))

            Text(
                text = "Settings",
                color = Color.White,
                fontSize = 16.sp,
                modifier = Modifier.padding(start = 16.dp),
                fontWeight = FontWeight.Medium
            )
        }

        //logout
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .padding(start = 16.dp, end = 16.dp, top = 16.dp, bottom = 16.dp)
                .clickable {
                    menuAction(HomeMenuAction.LOGOUT)
                }
        ) {
            Icon(
                painter = painterResource(id = com.vikas.shoeapp.R.drawable.ic_logout),
                contentDescription = "Logout",
                tint = Color.White,
                modifier = Modifier.size(24.dp)
            )

            Spacer(modifier = Modifier.width(16.dp))

            Text(
                text = "Logout",
                color = Color.White,
                fontSize = 16.sp,
                modifier = Modifier.padding(start = 16.dp),
                fontWeight = FontWeight.Medium
            )
        }

        //app version
        Text(
            text = "App version: ${BuildConfig.VERSION_NAME}",
            color = Color.White.copy(alpha = .4f),
            fontSize = 16.sp,
            modifier = Modifier.padding(start = 16.dp),
            fontWeight = FontWeight.Medium,
        )

    }

}


@Composable
@Preview(showBackground = true, showSystemUi = true)
fun PreviewHomeComponent() {
    HomeComponent()
}
