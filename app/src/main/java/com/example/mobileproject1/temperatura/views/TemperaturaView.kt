package com.example.mobileproject1.temperatura.views

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.mobileproject1.R
import com.example.mobileproject1.temperatura.viewmodels.TemperatureViewModel

@Composable
fun TemperatureScreen(viewModel: TemperatureViewModel = viewModel()) {
    var celsius by remember { mutableStateOf("") }
    val fahrenheit by viewModel.fahrenheit
    val context = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        OutlinedTextField(
            value = celsius,
            onValueChange = { celsius = it },
            label = { Text(stringResource(R.string.enter_celsius)) },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            singleLine = true,
            modifier = Modifier.fillMaxWidth()
        )

        Button(
            onClick = {
                viewModel.convertToFahrenheit(celsius, context) // ✅ Pass Context for Localization
                if (fahrenheit == context.getString(R.string.invalid_input)) {
                    Toast.makeText(context, context.getString(R.string.toast_invalid_number), Toast.LENGTH_SHORT).show()
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(stringResource(R.string.convert))
        }

        Text(text = fahrenheit, style = MaterialTheme.typography.headlineMedium)

        Image(
            painter = painterResource(id = R.drawable.termostato),
            contentDescription = stringResource(R.string.thermostat_description),
            modifier = Modifier.fillMaxWidth()
        )
    }
}

