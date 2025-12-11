package mx.edu.utez.dulcedelicias.ui.screens.viewmodel

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import mx.edu.utez.dulcedelicias.data.network.model.DetallePedido
import mx.edu.utez.dulcedelicias.data.network.model.Producto
import mx.edu.utez.dulcedelicias.data.network.repository.PedidoRepository

class CarritoViewModel(
    private val pedidoRepository: PedidoRepository
) : ViewModel() {

    // Estado del carrito como lista observable
    private val _carrito = mutableStateListOf<DetallePedido>()
    val carrito: List<DetallePedido> get() = _carrito

    // Agregar producto al carrito
    fun agregarProducto(producto: Producto) {
        val existente = _carrito.find { it.idProducto == producto.id }
        if (existente != null) {
            existente.cantidad += 1
        } else {
            _carrito.add(
                DetallePedido(
                    idProducto = producto.id,
                    cantidad = 1,
                    precioUnitario = producto.precio,
                    producto = producto
                )
            )
        }
    }

    // Incrementar cantidad
    fun incrementar(detalle: DetallePedido) {
        detalle.cantidad++
    }

    // Decrementar cantidad
    fun decrementar(detalle: DetallePedido) {
        if (detalle.cantidad > 1) {
            detalle.cantidad--
        } else {
            _carrito.remove(detalle)
        }
    }

    // Confirmar pedido
    fun confirmarPedido(
        nombreCliente: String,
        ubicacion: String,
        envio: Double = 3.50,
        onSuccess: (Int) -> Unit,
        onError: (String) -> Unit
    ) {
        val subtotal = _carrito.sumOf { it.producto.precio * it.cantidad }
        val total = subtotal + envio

        pedidoRepository.realizarPedido(
            nombreCliente,
            ubicacion,
            total,
            _carrito,
            onSuccess,
            onError
        )
    }
}