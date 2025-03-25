package com.example.mobileproject1.sum.viewmodels

import androidx.lifecycle.ViewModel
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.State

class CalculatorViewModel : ViewModel() {
    private val _sum = mutableStateOf(0)
    val sum: State<Int> = _sum

    fun calculateSum(num1: Int, num2: Int) {
        _sum.value = num1 + num2
    }
}
