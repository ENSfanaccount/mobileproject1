package com.example.mobileproject1.thirdpartial

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.unit.sp
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
fun ThirdPartialScreen(navController: NavController) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Button(onClick = {
            navController.navigate("student_list")
        }) {
            Text("Lista De Estudiantes")
        }
        Button(onClick = {
            navController.navigate("student_list_2")
        }) {
            Text("Lista De Estudiantes chafa")
        }
        Button(onClick = {
            navController.navigate("location_list")
        }) {
            Text("lista de racket en chihuahua")
        }
    }
}