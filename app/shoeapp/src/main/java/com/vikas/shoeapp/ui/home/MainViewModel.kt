package com.vikas.shoeapp.ui.home

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vikas.shoeapp.data.CarouselDataModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {

    val screenState = mutableStateOf<UiState>(UiState.Home)

    val buttonState = mutableStateOf(ButtonState.DEFAULT)

    val cartFlow = MutableSharedFlow<Boolean>()

    sealed class UiState {
        class Details(val carouselDataModel: CarouselDataModel) : UiState()
        object Home : UiState()
    }

    fun onBackClick() {
        screenState.value = UiState.Home
    }

    fun changeButtonState() {
        if (buttonState.value == ButtonState.LOADED) {
            viewModelScope.launch {
                cartFlow.emit(true)
            }
            return
        }
        viewModelScope.launch {
            buttonState.value = ButtonState.LOADING
            delay(1500)
            buttonState.value = ButtonState.LOADED
        }
    }

}

