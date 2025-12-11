package mx.edu.utez.dulcedelicias.data.network.dao

import android.content.Context
import mx.edu.utez.dulcedelicias.data.network.api.DulceDeliciasAPI
import mx.edu.utez.dulcedelicias.data.network.model.Producto

// ----------------------------------------------------
// INTERFAZ: Define qué métodos debe tener el DAO
// ----------------------------------------------------
interface ProductoDao {
    fun getAllProductos(
        onSuccess: (List<Producto>) -> Unit,
        onError: (String) -> Unit
    )
    // Aquí irían otros métodos si hubiera necesidad de obtener un producto por ID o buscar.
}

// ----------------------------------------------------
// IMPLEMENTACIÓN: Conecta la interfaz con la API
// ----------------------------------------------------
class ProductoDaoImpl(private val context: Context) : ProductoDao {

    // Instancia de la clase que hace las peticiones HTTP
    private val api = DulceDeliciasAPI(context)

    /**
     * Implementación del método para obtener todos los productos disponibles.
     * Simplemente delega la llamada a la capa API.
     */
    override fun getAllProductos(
        onSuccess: (List<Producto>) -> Unit,
        onError: (String) -> Unit
    ) {
        api.getAllProductos(onSuccess, onError)
    }
}