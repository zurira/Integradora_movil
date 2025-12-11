package mx.edu.utez.dulcedelicias.data.network.model

import java.math.BigDecimal

data class Producto(
    val id: Int = 0,
    val nombre: String,
    val descripcion: String?,
    val precio: Double, // Usar BigDecimal para manejar dinero con precisión
    val stock: Int,
    val disponible: Boolean,
    val imagenUrl: String?
)