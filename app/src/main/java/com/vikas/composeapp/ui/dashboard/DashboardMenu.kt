package com.vikas.composeapp.ui.dashboard

import com.vikas.composeapp.R

enum class DashboardMenu(val title: String, val icon: Int) {
    HOME("Home", com.vikas.shoeapp.R.drawable.ic_home),
    NOTIFICATION("Notification", com.vikas.shoeapp.R.drawable.ic_notification),
    FAVORITE("Favorite", com.vikas.shoeapp.R.drawable.ic_heart),
    CART("Cart", com.vikas.shoeapp.R.drawable.ic_shop),
    PROFILE("Profile", com.vikas.shoeapp.R.drawable.ic_profile),
}

enum class AppProject(val title: String, val image: Int) {
    SHOE_APP("Shoe App", R.drawable.shoe_background),
    PIZZA_APP("Pizza app", R.drawable.pizza_background),
    UPCOMING_APP("Upcoming App", R.drawable.upcoming_background),
}