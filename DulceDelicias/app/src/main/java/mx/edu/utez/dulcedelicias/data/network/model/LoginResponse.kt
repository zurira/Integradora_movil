package mx.edu.utez.dulcedelicias.data.network.model

data class LoginResponse(
    val exito: Boolean,
    val mensaje: String,
    val idUsuario: Int? // Puede ser null si el login falla
)