import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.shoppingcartapplication.data.datasource.CartDataSource
import com.example.shoppingcartapplication.data.repository.CartRepositoryImpl
import com.example.shoppingcartapplication.domain.model.CartItem
import com.example.shoppingcartapplication.domain.usecase.AddItemToCartUseCase
import com.example.shoppingcartapplication.domain.usecase.GetCartItemsUseCase
import com.example.shoppingcartapplication.presentation.ShoppingCartVM
import com.example.shoppingcartapplication.presentation.intent.CartIntent
import com.example.shoppingcartapplication.presentation.state.ViewState

@Composable
fun CartScreen() {

    val localDataSource = remember { CartDataSource() }
    val repository = remember { CartRepositoryImpl(localDataSource) }
    val getCartItemsUseCase = remember { GetCartItemsUseCase(repository) }
    val addItemToCartUseCase = remember { AddItemToCartUseCase(repository) }
    val viewModel = remember { ShoppingCartVM(getCartItemsUseCase, addItemToCartUseCase) }

    val state by viewModel.state.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.processIntent(CartIntent.AddItem(CartItem(id = 1, name = "Apple", price = 3.0, quantity = 2)))
        viewModel.processIntent(CartIntent.AddItem(CartItem(id = 2, name = "Banana", price = 2.5, quantity = 5)))
        viewModel.processIntent(CartIntent.AddItem(CartItem(id = 3, name = "Milk", price = 7.0, quantity = 1)))

        viewModel.processIntent(CartIntent.LoadCartItems)
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "Shopping Cart Items",
            style = MaterialTheme.typography.titleLarge,
            color = Color.Black
        )

        Spacer(modifier = Modifier.height(16.dp))

        when (val currentState = state) {
            is ViewState.Loading -> {
                Text(text = "Loading...")
            }

            is ViewState.Success -> {
                val cartItems = currentState.cartItems

                LazyColumn(
                    modifier = Modifier.fillMaxSize()
                ) {
                    items(cartItems) { item ->
                        CartItemRow(item = item)
                        Divider()
                    }
                }
            }

            is ViewState.Error -> {
                Text(
                    text = "Error: ${currentState.message}",
                    color = Color.Red
                )
            }
        }
    }
}

@Composable
fun CartItemRow(item: CartItem) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(
            modifier = Modifier.weight(1f)
        ) {
            Text(text = item.name, style = MaterialTheme.typography.bodyLarge)
            Text(text = "Price: \$${item.price}", style = MaterialTheme.typography.bodyMedium)
            Text(text = "Quantity: ${item.quantity}", style = MaterialTheme.typography.bodySmall)
        }

    }
}
