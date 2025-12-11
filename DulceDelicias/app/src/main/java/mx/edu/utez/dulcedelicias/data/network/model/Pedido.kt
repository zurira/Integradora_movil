package mx.edu.utez.dulcedelicias.data.network.model

data class Pedido (
    val id: Int,
    val nombreCliente: String,
    val ubicacion: String,
    val elementos : List<Carrito>,
    val total: Double
)