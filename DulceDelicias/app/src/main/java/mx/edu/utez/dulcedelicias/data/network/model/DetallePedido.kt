package mx.edu.utez.dulcedelicias.data.network.model

import java.math.BigDecimal

data class DetallePedido(
    val idDetalle: Int = 0,
    val idPedido: Int,
    val idProducto: Int,
    val cantidad: Int,
    val precioUnitario: Double,
    val producto: Producto
)