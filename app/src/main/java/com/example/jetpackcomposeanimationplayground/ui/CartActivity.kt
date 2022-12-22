package com.example.jetpackcomposeanimationplayground.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.Text

class CartActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Text(text = "Cart")
        }
    }

    companion object {
        fun intent(context: Context) = Intent(context, CartActivity::class.java)
    }

}