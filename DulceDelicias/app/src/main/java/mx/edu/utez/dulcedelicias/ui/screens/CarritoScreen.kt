package mx.edu.utez.dulcedelicias.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import mx.edu.utez.dulcedelicias.data.network.model.DetallePedido
import mx.edu.utez.dulcedelicias.ui.screens.components.CarritoList

@Composable
fun CarritoScreen(
    detallePedidos: List<DetallePedido>,
    onIncrement: (DetallePedido) -> Unit,
    onDecrement: (DetallePedido) -> Unit,
    onConfirmarPedido: () -> Unit
) {
    val subtotal = detallePedidos.sumOf { it.producto.precio * it.cantidad }
    val envio = 3.50
    val total = subtotal + envio

    Scaffold(
        bottomBar = {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text("Subtotal: $${"%.2f".format(subtotal)}")
                Text("Envío: $${"%.2f".format(envio)}")
                Text("Total: $${"%.2f".format(total)}", style = MaterialTheme.typography.titleMedium)

                Spacer(modifier = Modifier.height(8.dp))

                Button(
                    onClick = onConfirmarPedido,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("Confirmar pedido")
                }
            }
        }
    ) { innerPadding ->
        CarritoList(
            detallePedidos = detallePedidos,
            onIncrement = onIncrement,
            onDecrement = onDecrement
        )
    }
}