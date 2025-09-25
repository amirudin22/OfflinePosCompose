package com.udin.kasir.ui

import androidx.compose.runtime.Composable
import androidx.compose.material.Text
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun DashboardScreen(navController: androidx.navigation.NavHostController) {
    Column(modifier = Modifier.padding(16.dp)) {
        Text("Dashboard (placeholder) — implement widgets: sales summary, quick products, shortcuts.")
    }
}
