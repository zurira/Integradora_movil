package mx.edu.utez.dulcedelicias.ui.screens.components

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import mx.edu.utez.dulcedelicias.data.network.model.Carrito

@Composable
fun CarritoList(
    carritos: List<Carrito>,
    onIncrement: (Carrito) -> Unit,
    onDecrement: (Carrito) -> Unit
) {
    LazyColumn(modifier = Modifier.fillMaxSize()) {
        items(carritos) { carrito ->
            CarritoCard(
                carrito = carrito,
                onIncrement = onIncrement,
                onDecrement = onDecrement
            )
        }
    }
}