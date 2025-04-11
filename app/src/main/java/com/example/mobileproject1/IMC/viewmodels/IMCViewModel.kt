package com.example.mobileproject1.IMC.viewmodels

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.compose.runtime.*
import com.example.mobileproject1.R

class BmiViewModel : ViewModel() {

    var bmi by mutableStateOf("")
        private set

    var message by mutableStateOf("")
        private set

    fun calculateBmi(weightKg: String, heightM: String, context: Context) {
        val weight = weightKg.toFloatOrNull()
        val height = heightM.toFloatOrNull()

        if (weight == null || height == null || height <= 0) {
            bmi = context.getString(R.string.invalid_input)
            message = ""
            return
        }

        val bmiValue = weight / (height * height)
        bmi = String.format("%.2f", bmiValue)

        message = if (bmiValue in 19.0..24.9) {
            context.getString(R.string.good_weight)
        } else {
            context.getString(R.string.bad_weight)
        }
    }
}