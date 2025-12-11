package mx.edu.utez.dulcedelicias.ui.screens

import android.content.Context
import android.widget.Toast
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
import androidx.navigation.NavHostController
import mx.edu.utez.dulcedelicias.data.network.model.DetallePedido
import mx.edu.utez.dulcedelicias.data.network.navigation.BottomBar
import mx.edu.utez.dulcedelicias.data.network.repository.PedidoRepository
import mx.edu.utez.dulcedelicias.ui.screens.components.CarritoList

@Composable
fun CarritoScreen(
    navController: NavHostController,
    detallePedidos: MutableList<DetallePedido>,
    pedidoRepository: PedidoRepository,
    context: Context
) {
    val subtotal = detallePedidos.sumOf { it.producto.precio * it.cantidad }
    val envio = 3.50
    val total = subtotal + envio

    fun confirmarPedido() {
        pedidoRepository.realizarPedido(
            nombreCliente = "Zraddai", // ← puedes obtenerlo del login
            ubicacion = "Tlaltizapán", // ← GPS o formulario
            total = total,
            itemsCarrito = detallePedidos,
            onSuccess = { idPedido ->
                Toast.makeText(context, "Pedido creado: $idPedido", Toast.LENGTH_LONG).show()
                detallePedidos.clear()
                navController.navigate(Screen.Home.route) // ← vuelve al home
            },
            onError = { error ->
                Toast.makeText(context, error, Toast.LENGTH_LONG).show()
            }
        )
    }

    Scaffold(
        bottomBar = { BottomBar(navController) } // ← navegación inferior
    ) { innerPadding ->
        Column(modifier = Modifier.padding(innerPadding)) {
            CarritoList(
                detallePedidos = detallePedidos,
                onIncrement = { it.cantidad++ },
                onDecrement = {
                    if (it.cantidad > 1) it.cantidad-- else detallePedidos.remove(it)
                }
            )

            Spacer(modifier = Modifier.height(16.dp))

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
                    onClick = { confirmarPedido() },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("Confirmar pedido")
                }
            }
        }
    }
}