package com.example.mobileproject1.IMC.views

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.platform.LocalContext
import com.example.mobileproject1.R
import com.example.mobileproject1.IMC.viewmodels.BmiViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BmiScreen()
        }
    }
}

@Composable
fun BmiScreen(viewModel: BmiViewModel = viewModel()) {
    var weight by remember { mutableStateOf("") }
    var height by remember { mutableStateOf("") }
    val context = LocalContext.current  // Get Context for localization

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(stringResource(id = R.string.bmi_calculator), style = MaterialTheme.typography.headlineLarge)

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = weight,
            onValueChange = { weight = it },
            label = { Text(stringResource(id = R.string.weight_kg)) },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = height,
            onValueChange = { height = it },
            label = { Text(stringResource(id = R.string.height_m)) },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = { viewModel.calculateBmi(weight, height, context) },  // Pass context
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(stringResource(id = R.string.calculate_bmi))
        }

        Spacer(modifier = Modifier.height(16.dp))

        if (viewModel.bmi.isNotEmpty()) {
            Text(text = stringResource(id = R.string.bmi_result, viewModel.bmi), style = MaterialTheme.typography.bodyLarge)
            Text(text = viewModel.message, style = MaterialTheme.typography.bodyLarge)
        }
    }
}
