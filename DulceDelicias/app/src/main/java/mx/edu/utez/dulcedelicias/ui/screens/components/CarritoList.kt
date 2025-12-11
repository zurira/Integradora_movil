package mx.edu.utez.dulcedelicias.ui.screens.components

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import mx.edu.utez.dulcedelicias.data.network.model.DetallePedido

@Composable
fun CarritoList(
    detallePedidos: List<DetallePedido>,
    onIncrement: (DetallePedido) -> Unit,
    onDecrement: (DetallePedido) -> Unit
) {
    LazyColumn(modifier = Modifier.fillMaxSize()) {
        items(detallePedidos) { carrito ->
            CarritoCard(
                detallePedido = carrito,
                onIncrement = onIncrement,
                onDecrement = onDecrement
            )
        }
    }
}