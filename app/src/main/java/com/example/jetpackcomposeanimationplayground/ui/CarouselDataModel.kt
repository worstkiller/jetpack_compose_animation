package com.example.jetpackcomposeanimationplayground.ui

import androidx.compose.ui.graphics.Color
import com.example.jetpackcomposeanimationplayground.R

data class CarouselDataModel(
    val resId: Int,
    val title: String,
    val description: String,
    val price: String,
    val color: Color
) {

    companion object {
        val listOfShoes = mutableListOf<CarouselDataModel>().apply {
            add(
                CarouselDataModel(
                    resId = R.drawable.nike_100,
                    title = "Nike",
                    description = "Airmax 100",
                    price = "₹ 15,000",
                    color = Color(0xFFeb4658)
                )
            )
            add(
                CarouselDataModel(
                    resId = R.drawable.reebok_102,
                    title = "Reebok",
                    description = "Epic React 2",
                    price = "₹ 20,000",
                    color = Color(0xFF6887cb)
                )
            )
            add(
                CarouselDataModel(
                    resId = R.drawable.under_103,
                    title = "Under Armour",
                    description = "Hovr 2022",
                    price = "₹ 10,000",
                    color = Color(0xFFfe7153)
                )
            )
            add(
                CarouselDataModel(
                    resId = R.drawable.adidas_104,
                    title = "Adidas",
                    description = "Supernova 200",
                    price = "₹ 6,500",
                    color = Color(0xFF45bfff)
                )
            )
        }

        val categories = listOf("New", "Featured", "Trending")
    }

}
