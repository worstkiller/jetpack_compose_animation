package com.example.jetpackcomposeanimationplayground.ui

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

class CartViewModel() : ViewModel() {

    val cartItems = mutableStateOf(CartDataModel.list)

    fun removeProduct(cartDataModel: CartDataModel) {
        cartItems.value = cartItems.value.filter { it != cartDataModel }
        Log.d("TAG++", cartItems.value.size.toString())
    }

}