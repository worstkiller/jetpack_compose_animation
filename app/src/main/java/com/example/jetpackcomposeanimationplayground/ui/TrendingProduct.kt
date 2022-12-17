package com.example.jetpackcomposeanimationplayground.ui

import com.example.jetpackcomposeanimationplayground.R

data class TrendingProduct(val name: String, val price: String, val image: Int) {

    companion object {
        val list = listOf(
            TrendingProduct("Nike Air Max 270", "$120", R.drawable.adidas_1032),
            TrendingProduct("Under Armour 109", "$190", R.drawable.under_1031),
            TrendingProduct("Adidas Neo", "$150", R.drawable.shoe_101),
        )
    }

}
