package com.example.mobileproject1.temperatura.viewmodels

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.State
import com.example.mobileproject1.R

class TemperatureViewModel : ViewModel() {
    private val _fahrenheit = mutableStateOf("")

    val fahrenheit: State<String> = _fahrenheit

    fun convertToFahrenheit(celsius: String, context: Context) {
        celsius.toDoubleOrNull()?.let {
            val fahrenheitValue = it * 9 / 5 + 32
            _fahrenheit.value = "$fahrenheitValue °F"
        } ?: run {
            _fahrenheit.value = context.getString(R.string.invalid_input)
        }
    }
}

