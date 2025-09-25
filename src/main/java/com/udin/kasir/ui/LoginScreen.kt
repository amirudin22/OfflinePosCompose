package com.udin.kasir.ui

import androidx.compose.runtime.Composable
import androidx.compose.material.*
import androidx.compose.foundation.layout.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController

@Composable
fun LoginScreen(navController: NavHostController) {
    Column(modifier = Modifier.padding(16.dp)) {
        Text("Login (placeholder)")
        Spacer(modifier = Modifier.height(12.dp))
        // TODO: real username/password fields connected to Room DB
        Button(onClick = { navController.navigate("dashboard") }, modifier = Modifier.fillMaxWidth()) {
            Text("Login (demo)")
        }
        Spacer(modifier = Modifier.height(8.dp))
        Button(onClick = { navController.navigate("register") }, modifier = Modifier.fillMaxWidth()) {
            Text("Register")
        }
    }
}
