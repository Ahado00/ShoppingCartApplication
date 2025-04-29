package com.example.shoppingcartapplication.presentation.intent

import com.example.shoppingcartapplication.domain.model.CartItem

/**
 *  Intent: Actions that can be performed
 *  (AddItem, RemoveItem, UpdateQuantity)
 */

sealed class CartIntent{
    data class AddItem(val item: CartItem) : CartIntent()
    data class RemoveItem(val itemId: Int) : CartIntent()
    data class UpdateQuantity(val itemId: Int, val quantity: Int) : CartIntent()
    object LoadCartItems : CartIntent()

}