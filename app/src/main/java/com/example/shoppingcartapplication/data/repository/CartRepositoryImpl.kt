package com.example.shoppingcartapplication.data.repository

import com.example.shoppingcartapplication.data.datasource.CartDataSource
import com.example.shoppingcartapplication.domain.model.CartItem
import com.example.shoppingcartapplication.domain.repository.CartRepository

class CartRepositoryImpl (private val cartData : CartDataSource) : CartRepository {
    override suspend fun getCartItems(): List<CartItem> {
        return cartData.getCartItems()
    }

    override suspend fun addItemToCart(item: CartItem) {
        return cartData.addItem(item)
    }


}
