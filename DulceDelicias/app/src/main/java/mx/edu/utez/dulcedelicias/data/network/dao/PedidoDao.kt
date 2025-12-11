package mx.edu.utez.dulcedelicias.data.network.dao

import android.content.Context
import mx.edu.utez.dulcedelicias.data.network.api.DulceDeliciasAPI
import mx.edu.utez.dulcedelicias.data.network.model.DetallePedido

// ----------------------------------------------------
// INTERFAZ: Define qué métodos debe tener el DAO
// ----------------------------------------------------
interface PedidoDao {
    fun crearPedido(
        nombreCliente: String,
        ubicacion: String,
        total: Double,
        itemsCarrito: List<DetallePedido>,
        onSuccess: (Int) -> Unit, // Retorna el ID del pedido creado
        onError: (String) -> Unit
    )
    // Aquí irían métodos para administradores (e.g., actualizar estado del pedido, listar pedidos).
}

// ----------------------------------------------------
// IMPLEMENTACIÓN: Conecta la interfaz con la API
// ----------------------------------------------------
class PedidoDaoImpl(private val context: Context) : PedidoDao {

    // Instancia de la clase que hace las peticiones HTTP
    private val api = DulceDeliciasAPI(context)

    /**
     * Implementación del método para crear un pedido.
     * Delega la serialización y el envío HTTP a la capa API.
     */
    override fun crearPedido(
        nombreCliente: String,
        ubicacion: String,
        total: Double,
        itemsCarrito: List<DetallePedido>,
        onSuccess: (Int) -> Unit,
        onError: (String) -> Unit
    ) {
        api.crearPedido(nombreCliente, ubicacion, total, itemsCarrito, onSuccess, onError)
    }
}