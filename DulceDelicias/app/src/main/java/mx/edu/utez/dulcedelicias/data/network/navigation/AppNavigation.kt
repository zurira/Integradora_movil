package mx.edu.utez.dulcedelicias.data.network.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import mx.edu.utez.dulcedelicias.ui.screens.components.AdminScreen
import mx.edu.utez.dulcedelicias.ui.screens.components.LoginScreen

@Composable
fun AppNavigation() {
    //Permite mover las pantallas entre ellas
    val navController = rememberNavController()

    //1. navCotroller 2. startDestination
    NavHost(
        navController =navController,
        startDestination = "login"
    ){
        composable("login"){
            LoginScreen(navController)
        }
        composable("adminscreen") {
            AdminScreen(navController)
        }

    }
}