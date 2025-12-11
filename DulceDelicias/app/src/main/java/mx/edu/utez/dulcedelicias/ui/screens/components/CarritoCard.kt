package mx.edu.utez.dulcedelicias.ui.screens.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import mx.edu.utez.dulcedelicias.data.network.model.DetallePedido


@Composable
fun CarritoCard(
    detallePedido: DetallePedido,
    onIncrement: (DetallePedido) -> Unit,
    onDecrement: (DetallePedido) -> Unit
) {
    Card(
        modifier = Modifier
            .padding(15.dp)
            .fillMaxWidth(),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Row(
            modifier = Modifier
                .padding(10.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Imagen del producto
            AsyncImage(
                model = detallePedido.producto.imagenUrl,
                contentDescription = "Imagen de postre",
                modifier = Modifier.size(80.dp),
                contentScale = ContentScale.Crop
            )

            Spacer(modifier = Modifier.width(10.dp))

            Column(modifier = Modifier.weight(1f)) {
                Text(text = detallePedido.producto.nombre, style = MaterialTheme.typography.titleMedium)
                Text(text = "$${"%.2f".format(detallePedido.producto.precio)}", style = MaterialTheme.typography.bodyMedium)

                Row(verticalAlignment = Alignment.CenterVertically) {
                    IconButton(onClick = { onDecrement(detallePedido) }) {
                        Icon(Icons.Filled.KeyboardArrowDown, contentDescription = "Disminuir cantidad")
                    }
                    Text(
                        text = detallePedido.cantidad.toString(),
                        style = MaterialTheme.typography.bodyLarge,
                        modifier = Modifier.padding(horizontal = 8.dp)
                    )
                    IconButton(onClick = { onIncrement(detallePedido) }) {
                        Icon(Icons.Filled.KeyboardArrowUp, contentDescription = "Aumentar cantidad")
                    }
                }
            }
        }
    }
}
/*@Composable
@Preview (showBackground = true)
fun CarritoCardPreview(){
    CarritoCard()
}*/
