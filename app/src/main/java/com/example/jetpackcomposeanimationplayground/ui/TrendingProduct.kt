package com.example.jetpackcomposeanimationplayground.ui

import com.example.jetpackcomposeanimationplayground.R

data class TrendingProduct(val name: String, val price: String, val image: Int) {
    companion object {
        val list = listOf(
            TrendingProduct("Nike Air Max 270", "₹12000", R.drawable.adidas_1032),
            TrendingProduct("Under Armour 109", "₹19000", R.drawable.under_1031),
            TrendingProduct("Adidas Neo", "₹15000", R.drawable.shoe_101),
        )
    }
}

data class CartDataModel(val name: String, val price: String, val image: Int) {
    companion object {
        val list = listOf(
            CartDataModel("Nike Air Max 270", "₹12000", R.drawable.adidas_1032),
            CartDataModel("Under Armour 109", "₹19000", R.drawable.under_1031),
            CartDataModel("Adidas Neo", "₹15000", R.drawable.shoe_101),
        )
    }
}
