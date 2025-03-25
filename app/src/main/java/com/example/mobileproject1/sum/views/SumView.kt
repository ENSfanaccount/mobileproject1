package com.example.mobileproject1.sum.views

import androidx.compose.foundation.background
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.mobileproject1.sum.viewmodels.CalculatorViewModel

@Composable
fun SumBox(viewModel: CalculatorViewModel = viewModel()) {
    var number1 by remember { mutableStateOf("") }
    var number2 by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center
    ) {
        OutlinedTextField(
            value = number1,
            onValueChange = { number1 = it },
            label = { Text("Enter first number") }
        )

        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = number2,
            onValueChange = { number2 = it },
            label = { Text("Enter second number") }
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = {
            val num1 = number1.toIntOrNull() ?: 0
            val num2 = number2.toIntOrNull() ?: 0
            viewModel.calculateSum(num1, num2)
        }) {
            Text("Calculate Sum")
        }

        Spacer(modifier = Modifier.height(16.dp))

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp)
                .background(Color.LightGray),
            contentAlignment = androidx.compose.ui.Alignment.Center
        ) {
            Text(text = "Result: ${viewModel.sum.value}", style = MaterialTheme.typography.bodyLarge)
        }
    }
}
