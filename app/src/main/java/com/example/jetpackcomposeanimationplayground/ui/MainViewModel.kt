package com.example.jetpackcomposeanimationplayground.ui

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {

    val screenState = mutableStateOf<UiState>(UiState.Home)

    val buttonState = mutableStateOf(ButtonState.DEFAULT)

    val cartFlow = MutableStateFlow(false)

    sealed class UiState {
        class Details(val carouselDataModel: CarouselDataModel) : UiState()
        object Home : UiState()
    }

    fun onBackClick() {
        screenState.value = UiState.Home
    }

    fun changeButtonState() {
        if (buttonState.value == ButtonState.LOADED) {
            cartFlow.tryEmit(true)
            return
        }
        viewModelScope.launch {
            buttonState.value = ButtonState.LOADING
            delay(3000)
            buttonState.value = ButtonState.LOADED
        }
    }

}

