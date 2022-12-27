package com.example.jetpackcomposeanimationplayground.ui

import com.example.jetpackcomposeanimationplayground.R
import kotlin.random.Random

data class TrendingProduct(val name: String, val price: String, val image: Int) {
    companion object {
        val list = listOf(
            TrendingProduct("Nike Air Max 270", "₹12000", R.drawable.adidas_1032),
            TrendingProduct("Under Armour 109", "₹19000", R.drawable.under_1031),
            TrendingProduct("Adidas Neo", "₹15000", R.drawable.shoe_101),
        )
    }
}

data class CartDataModel(
    val id: Int,
    val name: String,
    val price: Int,
    val image: Int,
    var quantity: Int = 1,

    ) {
    companion object {
        val list = listOf(
            CartDataModel(id = 101, "Nike Air Max 270", 12000, R.drawable.adidas_1032),
            CartDataModel(id = 102, "Under Armour 109", 19000, R.drawable.under_1031),
            CartDataModel(id = 103, "Adidas Neo", 15000, R.drawable.shoe_101),
        )
    }
}
