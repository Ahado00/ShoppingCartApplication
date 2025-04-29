package com.example.shoppingcartapplication.domain.usecase

import com.example.shoppingcartapplication.domain.model.CartItem
import com.example.shoppingcartapplication.domain.repository.CartRepository

class GetCartItemsUseCase(private val repository: CartRepository) {
    suspend operator fun invoke(): List<CartItem> {
        return repository.getCartItems()
    }
}
