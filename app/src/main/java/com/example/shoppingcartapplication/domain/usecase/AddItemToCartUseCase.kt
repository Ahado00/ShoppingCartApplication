package com.example.shoppingcartapplication.domain.usecase

import com.example.shoppingcartapplication.domain.model.CartItem
import com.example.shoppingcartapplication.domain.repository.CartRepository

class AddItemToCartUseCase(private val repository: CartRepository) {
    suspend operator fun invoke(item: CartItem) {
        repository.addItemToCart(item)
    }
}