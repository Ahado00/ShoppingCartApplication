package com.example.shoppingcartapplication.domain.repository

import com.example.shoppingcartapplication.domain.model.CartItem
import kotlinx.coroutines.flow.Flow

interface CartRepository {
    suspend fun getCartItems(): List<CartItem>
    suspend fun addItemToCart(item: CartItem)
}