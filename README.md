# Shopping Cart Application
## MVI with Clean Architecture Implementation

### Components:
- **Model:**
Represents the data structure for cart items (CartItem).

- **View:**
Represents the UI components that display the cart items and interact with the user.

- **Intent:**
Represents user actions like adding, removing, and updating item quantities.

- **ViewModel:**
Responsible for processing the intents and emitting new states using StateFlow.

- **State:**
A sealed class representing the UI states (Loading, Success, Error).
