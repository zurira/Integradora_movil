package mx.edu.utez.dulcedelicias.ui.screens.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import mx.edu.utez.dulcedelicias.R
import mx.edu.utez.dulcedelicias.ui.viewmodel.LoginViewModel

@Composable
fun LoginScreen(
    navController: NavController,
    // Inicializa el ViewModel usando Factory por defecto de AndroidViewModel
    viewModel: LoginViewModel = viewModel()
) {
    var usuario by remember { mutableStateOf("") }
    var contrasena by remember { mutableStateOf("") }

    // Observamos los LiveData del ViewModel
    val mensaje by viewModel.mensaje.observeAsState("")
    val loginExitoso by viewModel.loginExitoso.observeAsState(false)

    // Efecto para la navegación cuando el login es exitoso
    LaunchedEffect(loginExitoso) {
        if (loginExitoso) {
            navController.navigate("adminscreen") {
                // Opcional: Para evitar volver a la pantalla de login con el botón de atrás
                popUpTo("login") { inclusive = true }
            }
            viewModel.resetLoginStatus()
        }
    }

    Column(
        modifier = Modifier.statusBarsPadding().fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(R.drawable.logo),
            contentDescription = "Inicio de sesión"
        )

        OutlinedTextField(
            value = usuario,
            onValueChange = { usuario = it },
            label = { Text(text = "Usuario") }
        )

        OutlinedTextField(
            value = contrasena,
            onValueChange = { contrasena = it },
            label = { Text(text = "Contraseña") },
            visualTransformation = PasswordVisualTransformation()
        )

        // Muestra el mensaje de estado (éxito, error, conexión)
        Text(text = mensaje)

        Row(
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier.padding(top = 16.dp)
        ) {
            Button(
                onClick = {
                    // 🚨 Llama al método de autenticación del ViewModel
                    viewModel.autenticar(usuario, contrasena)
                }
            ) { Text(text = "Iniciar sesión") }

            Button(onClick = {
                // Aquí podrías implementar el cierre de la aplicación o navegación a otra pantalla
            }) { Text(text = "Cerrar") }
        }
    }
}

// El Preview puede permanecer igual o usar el ViewModel si lo inicializas
// (aunque para el Preview simple no es estrictamente necesario)
@Preview(showBackground = true, showSystemUi = true)
@Composable
fun LoginScreenPreview() {
    val navController = rememberNavController()
    LoginScreen(navController = navController)
}