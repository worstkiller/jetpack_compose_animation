package com.vikas.composeapp.ui.home

enum class HomeMenu(val title: String, val icon: Int) {
    HOME("Home", com.vikas.shoeapp.R.drawable.ic_home),
    NOTIFICATION("Notification", com.vikas.shoeapp.R.drawable.ic_notification),
    FAVORITE("Favorite", com.vikas.shoeapp.R.drawable.ic_heart),
    CART("Cart", com.vikas.shoeapp.R.drawable.ic_shop),
    PROFILE("Profile", com.vikas.shoeapp.R.drawable.ic_profile),
}

sealed class HomeMenuAction {
    object CLOSE : HomeMenuAction()
    object LOGOUT : HomeMenuAction()
    object SETTINGS : HomeMenuAction()
    data class MenuSelected(val menu: HomeMenu) : HomeMenuAction()
}