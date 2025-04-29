package com.example.shoppingcartapplication.data.datasource

import com.example.shoppingcartapplication.domain.model.CartItem

class CartDataSource {
    private val cartItems = mutableListOf<CartItem>()

    suspend fun getCartItems(): List<CartItem> {
        return cartItems.toList()
    }

    suspend fun addItem(item: CartItem) {
        cartItems.add(item)
    }
}