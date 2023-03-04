package com.vikas.foodorder

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.rememberPagerState
import com.vikas.foodorder.theme.JetpackComposeAnimationPlaygroundTheme

class FoodAppMainActivity : ComponentActivity() {

    @OptIn(ExperimentalPagerApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JetpackComposeAnimationPlaygroundTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    ProductDetailsComponent()
                }
            }
        }
    }

    companion object {
        fun getIntent(context: Context): Intent {
            return Intent(context, FoodAppMainActivity::class.java)
        }
    }

}


@OptIn(ExperimentalPagerApi::class)
@Composable
@Preview(showBackground = true, showSystemUi = true)
fun DefaultPreview() {
    val pagerState = rememberPagerState()
    JetpackComposeAnimationPlaygroundTheme {
        ProductDetailsComponent()
    }
}