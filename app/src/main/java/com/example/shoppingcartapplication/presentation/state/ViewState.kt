package com.example.shoppingcartapplication.presentation.state

import com.example.shoppingcartapplication.domain.model.CartItem

/**
 * State: A sealed class representing UI states (Loading, Success, Error)
 */
sealed class ViewState{
    object Loading : ViewState()
    data class Success (val cartItems: List<CartItem>) : ViewState()
    data class Error(val message: String) : ViewState()
}