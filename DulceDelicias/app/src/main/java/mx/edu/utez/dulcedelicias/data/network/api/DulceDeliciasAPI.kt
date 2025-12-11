package mx.edu.utez.dulcedelicias.data.network.api

import android.content.Context
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import mx.edu.utez.dulcedelicias.data.network.VolleySingleton
// Asegúrate de que los modelos están en la ruta correcta
import mx.edu.utez.dulcedelicias.data.network.model.Producto
import mx.edu.utez.dulcedelicias.data.network.model.DetallePedido
import mx.edu.utez.dulcedelicias.data.network.model.LoginResponse
import org.json.JSONArray
import org.json.JSONObject
import java.math.BigDecimal

class DulceDeliciasAPI(private val context: Context) {
    val baseURL = "http://192.168.1.73:3000/api"

    // --- Adaptador para JSONObject para obtener BigDecimal (Necesario si usas BigDecimal) ---
    private fun JSONObject.getBigDecimal(name: String): BigDecimal {
        return try {
            BigDecimal(this.getString(name))
        } catch (e: Exception) {
            BigDecimal.ZERO
        }
    }

    fun getAllProductos(
        onSuccess: (List<Producto>) -> Unit,
        onError: (String) -> Unit
    ){
        val url = "$baseURL/productos"

        val listener = Response.Listener<JSONObject>{ response ->
            val lista = mutableListOf<Producto>()

            val productosArray = response.getJSONArray("productos")

            for(i in 0 until productosArray.length()){
                val objeto = productosArray.getJSONObject(i)
                lista.add(
                    Producto(
                        id = objeto.getInt("id"),
                        nombre = objeto.getString("nombre"),
                        descripcion = objeto.optString("descripcion"),
                        precio = objeto.getDouble("precio"),
                        stock = objeto.getInt("stock"),
                        disponible = objeto.getBoolean("disponible"),
                        imagenUrl = objeto.optString("imagen_url")
                    )
                )
            }
            onSuccess(lista)
        }

        val errorListener = Response.ErrorListener{ error ->
            onError(error.message ?: "Error de red al obtener productos.")
        }

        val request = JsonObjectRequest(Request.Method.GET, url, null, listener, errorListener)
        VolleySingleton.getInstance(context).add(request)
    }

    fun crearPedido(
        nombreCliente: String,
        ubicacion: String,
        total: Double,
        itemsCarrito: List<DetallePedido>,
        onSuccess: (Int) -> Unit,
        onError: (String) -> Unit
    ){
        val url = "$baseURL/pedidos"

        val pedidoJson = JSONObject().apply {
            put("nombre_cliente", nombreCliente)
            put("ubicacion", ubicacion)
            put("total", total)
        }

        val detallesArray = JSONArray()
        itemsCarrito.forEach { item ->
            val detalleJson = JSONObject().apply {
                put("idProducto", item.idProducto)
                put("cantidad", item.cantidad)
                // El precioUnitario lo saca el servidor
            }
            detallesArray.put(detalleJson)
        }

        val body = JSONObject().apply {
            put("pedido", pedidoJson)
            put("detalles", detallesArray)
        }

        val listener = Response.Listener<JSONObject>{ response ->
            if(response.getBoolean("exito")){
                onSuccess(response.getInt("idPedido"))
            } else {
                onError(response.getString("mensaje"))
            }
        }

        val errorListener = Response.ErrorListener{ error ->
            onError(error.message ?: "Error al conectar con el servidor al crear pedido.")
        }

        val request = JsonObjectRequest(Request.Method.POST, url, body, listener, errorListener)
        VolleySingleton.getInstance(context).add(request)
    }

    // Archivo: mx.edu.utez.dulcedelicias.data.network.api/DulceDeliciasAPI.kt

// ... (dentro de la clase DulceDeliciasAPI) ...

    // ===================================
    // C. AUTENTICACIÓN (Staff/Admin)
    // ===================================

    /**
     * Intento de inicio de sesión de un administrador.
     * Corresponde a POST /api/iniciar_sesion
     */
    fun iniciarSesion(
        nombreUsuario: String,
        clave: String,
        onSuccess: (LoginResponse) -> Unit, // Cambiado para devolver el modelo de respuesta
        onError: (String) -> Unit
    ){
        val url = "$baseURL/iniciar_sesion"
        val metodo = Request.Method.POST

        val body = JSONObject().apply {
            put("nombreUsuario", nombreUsuario)
            put("clave", clave)
        }

        val listener = Response.Listener<JSONObject>{ response ->
            // Mapeamos la respuesta JSON al modelo LoginResponse
            val loginResponse = LoginResponse(
                exito = response.getBoolean("exito"),
                mensaje = response.getString("mensaje"),
                idUsuario = response.optInt("idUsuario").let { if (it == 0) null else it }
            )
            onSuccess(loginResponse)
        }

        val errorListener = Response.ErrorListener{ error ->
            onError(error.message ?: "Error de conexión o credenciales incorrectas.")
        }

        val request = JsonObjectRequest(metodo, url, body, listener, errorListener)
        VolleySingleton.getInstance(context).add(request)
    }
}