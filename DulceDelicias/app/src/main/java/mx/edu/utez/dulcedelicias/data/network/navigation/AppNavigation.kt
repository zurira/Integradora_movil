package mx.edu.utez.dulcedelicias.data.network.navigation

import android.content.Context
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import mx.edu.utez.dulcedelicias.data.network.repository.PedidoRepository
import mx.edu.utez.dulcedelicias.ui.screens.CarritoScreen
import mx.edu.utez.dulcedelicias.ui.screens.components.AdminScreen
import mx.edu.utez.dulcedelicias.ui.screens.components.LoginScreen
import mx.edu.utez.dulcedelicias.ui.screens.viewmodel.CarritoViewModel

@Composable
fun AppNavigation(context: Context) {
    val navController = rememberNavController()

    // Instancia del repositorio y ViewModel
    val pedidoRepository = remember { PedidoRepository(context) }
    val carritoViewModel = remember { CarritoViewModel(pedidoRepository) }

    NavHost(
        navController = navController,
        startDestination = Screen.Carrito.route
    ) {
        composable(Screen.Carrito.route) {
            CarritoScreen(
                navController = navController,
                viewModel = carritoViewModel,
                context = context
            )
        }
        composable("login") { LoginScreen(navController) }
        composable("adminscreen") { AdminScreen(navController) }
    }
}

sealed class Screen(val route: String) {
    object Home : Screen("home")
    object Carrito : Screen("carrito")
}

@Composable
fun BottomBar(navController: NavHostController) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    NavigationBar {
        NavigationBarItem(
            icon = { Icon(Icons.Default.Home, contentDescription = "Inicio") },
            label = { Text("Inicio") },
            selected = currentRoute == Screen.Home.route,
            onClick = {
                if (currentRoute != Screen.Home.route) {
                    navController.navigate(Screen.Home.route) {
                        // Evita duplicar pantallas en el stack
                        popUpTo(navController.graph.startDestinationId) { saveState = true }
                        launchSingleTop = true
                        restoreState = true
                    }
                }
            }
        )
        NavigationBarItem(
            icon = { Icon(Icons.Default.ShoppingCart, contentDescription = "Carrito") },
            label = { Text("Carrito") },
            selected = currentRoute == Screen.Carrito.route,
            onClick = {
                if (currentRoute != Screen.Carrito.route) {
                    navController.navigate(Screen.Carrito.route) {
                        popUpTo(navController.graph.startDestinationId) { saveState = true }
                        launchSingleTop = true
                        restoreState = true
                    }
                }
            }
        )
    }
}