package com.example.shoppingcartapplication.presentation

import app.cash.turbine.test
import com.example.shoppingcartapplication.domain.model.CartItem
import com.example.shoppingcartapplication.domain.usecase.AddItemToCartUseCase
import com.example.shoppingcartapplication.domain.usecase.GetCartItemsUseCase
import com.example.shoppingcartapplication.presentation.intent.CartIntent
import com.example.shoppingcartapplication.presentation.state.ViewState
import io.mockk.coEvery
import io.mockk.mockk
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Test

@ExperimentalCoroutinesApi
class ShoppingCartVMTest {

    private val getCartItemsUseCase: GetCartItemsUseCase = mockk()
    private val addItemToCartUseCase: AddItemToCartUseCase = mockk()
    private lateinit var viewModel: ShoppingCartVM

    private val testDispatcher = UnconfinedTestDispatcher()

    @Before
    fun setup() {
        Dispatchers.setMain(testDispatcher)
        viewModel = ShoppingCartVM(getCartItemsUseCase, addItemToCartUseCase)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `processIntent LoadCartItems emits Loading and Success`() = runTest {
        val fakeItems = listOf(CartItem(1, "Apple", 2.0, 1))
        coEvery { getCartItemsUseCase() } returns fakeItems

        viewModel.state.test {
            viewModel.processIntent(CartIntent.LoadCartItems)

            assertEquals(ViewState.Loading, awaitItem())
            assertEquals(ViewState.Success(fakeItems), awaitItem())

            cancelAndIgnoreRemainingEvents()
        }
    }
}