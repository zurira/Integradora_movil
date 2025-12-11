package mx.edu.utez.dulcedelicias.ui.screens.components

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import mx.edu.utez.dulcedelicias.data.network.model.Producto

@Composable
fun ProductoList(
    productos: List<Producto>,
    onEditar: (Producto) -> Unit,
    onEliminar: (Producto) -> Unit
) {
    LazyColumn {
        items(productos) { producto ->
            ProductoCard(
                producto = producto,
                onEditar = { onEditar(producto) },
                onEliminar = { onEliminar(producto) }
            )
        }
    }
}