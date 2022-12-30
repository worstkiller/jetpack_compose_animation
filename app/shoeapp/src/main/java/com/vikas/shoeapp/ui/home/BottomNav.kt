package com.vikas.shoeapp.ui.home

import com.vikas.shoeapp.R

enum class BottomNav(val route: String, val icon: Int, val title: String) {
    Home("home", R.drawable.ic_home, "Home"),
    Search("favorite", R.drawable.ic_heart, "Favorite"),
    Profile("profile", R.drawable.ic_profile, "Profile"),
    Favorites("cart", R.drawable.ic_shop, "Cart"),
    Settings("settings", R.drawable.ic_settings, "Settings")
}