package mx.edu.utez.dulcedelicias.data.network.repository

import android.content.Context
import mx.edu.utez.dulcedelicias.data.network.dao.PedidoDaoImpl
import mx.edu.utez.dulcedelicias.data.network.dao.ProductoDaoImpl
import mx.edu.utez.dulcedelicias.data.network.model.Producto
import mx.edu.utez.dulcedelicias.data.network.model.DetallePedido

class ProductoRepository(context: Context) {
    private val productoDao = ProductoDaoImpl(context)

    fun obtenerProductos(
        onSuccess: (List<Producto>) -> Unit,
        onError: (String) -> Unit
    ) {
        productoDao.getAllProductos(onSuccess, onError)
    }
}

class PedidoRepository(context: Context) {
    private val pedidoDao = PedidoDaoImpl(context)

    fun realizarPedido(
        nombreCliente: String,
        ubicacion: String,
        total: Double,
        itemsCarrito: List<DetallePedido>,
        onSuccess: (Int) -> Unit,
        onError: (String) -> Unit
    ) {
        if (itemsCarrito.isEmpty() || total <= 0) {
            onError("El carrito está vacío o el total es cero.")
            return
        }

        pedidoDao.crearPedido(nombreCliente, ubicacion, total, itemsCarrito, onSuccess, onError)
    }
}