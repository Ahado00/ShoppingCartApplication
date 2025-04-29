package com.example.shoppingcartapplication.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.shoppingcartapplication.domain.model.CartItem
import com.example.shoppingcartapplication.domain.usecase.AddItemToCartUseCase
import com.example.shoppingcartapplication.domain.usecase.GetCartItemsUseCase
import com.example.shoppingcartapplication.presentation.intent.CartIntent
import com.example.shoppingcartapplication.presentation.state.ViewState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch


class ShoppingCartVM(
    private val getCartItemsUseCase: GetCartItemsUseCase,
    private val addItemToCartUseCase: AddItemToCartUseCase
) : ViewModel() {

    private val _state = MutableStateFlow<ViewState>(ViewState.Loading)
    val state: StateFlow<ViewState> = _state

    fun processIntent(intent: CartIntent) {
        when (intent) {
            is CartIntent.LoadCartItems -> {
                loadCartItems()
            }
            is CartIntent.AddItem -> {
                addItem(intent.item)
            }
            is CartIntent.RemoveItem -> {
                // Optional to implement
            }
            is CartIntent.UpdateQuantity -> {
                // Optional to implement
            }
        }
    }

    private fun loadCartItems() {
        viewModelScope.launch {
            _state.value = ViewState.Loading
            try {
                val items = getCartItemsUseCase()
                _state.value = ViewState.Success(items)
            } catch (e: Exception) {
                _state.value = ViewState.Error(e.message ?: "Unknown Error")
            }
        }
    }

    private fun addItem(item: CartItem) {
        viewModelScope.launch {
            addItemToCartUseCase(item)
            loadCartItems()
        }
    }


}
