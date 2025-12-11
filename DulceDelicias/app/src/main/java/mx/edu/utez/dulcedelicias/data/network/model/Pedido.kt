package mx.edu.utez.dulcedelicias.data.network.model

import java.math.BigDecimal
import java.util.Date

data class Pedido(
    val id: Int = 0,

    // Datos del Cliente
    val nombreCliente: String,
    val ubicacion: String, // Coordenadas o Dirección obtenida del GPS/formulario

    // Datos del Pedido
    val total: Double,
    val estado: String = "Pendiente", // Ej: "Pendiente", "Enviado", "Entregado"
    val fechaPedido: Date?,
    val fechaEntrega: Date?
)