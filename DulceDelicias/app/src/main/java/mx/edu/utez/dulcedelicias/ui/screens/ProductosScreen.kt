package mx.edu.utez.dulcedelicias.ui.screens

import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import mx.edu.utez.dulcedelicias.data.network.model.Producto
import mx.edu.utez.dulcedelicias.ui.screens.components.ProductoList

@Composable
fun GestionProductosScreen(
    productos: List<Producto>,
    onAgregarProducto: () -> Unit,
    onEditarProducto: (Producto) -> Unit,
    onEliminarProducto: (Producto) -> Unit
) {
    Scaffold(
        modifier = Modifier.statusBarsPadding(),
        floatingActionButton = {
            FloatingActionButton(
                onClick = {}
            ) {
                Icon(
                    imageVector = Icons.Filled.Add,
                    contentDescription = "Agregar"
                )
            }
        }
    ) { innerpadding ->
        ProductoList(productos) { }
    }
}