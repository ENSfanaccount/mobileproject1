package com.example.mobileproject1.ids

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun IdsView(navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center
    ) {
        Button(
            onClick = { navController.navigate("IMCRoute") },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Ir a IMC")
        }
        Button(
            onClick = { navController.navigate("temperaturaRoute") },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Ir a temperatura")
        }
        Button(
            onClick = { navController.navigate("SUMRoute") },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Ir a SUM")
        }
    }
}