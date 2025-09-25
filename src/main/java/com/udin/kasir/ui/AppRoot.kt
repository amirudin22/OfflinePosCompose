package com.udin.kasir.ui

import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

@Composable
fun AppRoot() {
    val navController = rememberNavController()
    Surface(color = MaterialTheme.colors.background) {
        NavHost(navController = navController, startDestination = "login") {
            composable("login") { LoginScreen(navController) }
            composable("register") { RegisterScreen(navController) }
            composable("dashboard") { DashboardScreen(navController) }
        }
    }
}
