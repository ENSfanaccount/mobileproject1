package com.example.mobileproject1.examentercerparcial.viewmodel


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mobileproject1.examentercerparcial.model.Student
import com.example.mobileproject1.examentercerparcial.network.RetrofitInstance
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

sealed class UiState {
    object Loading : UiState()
    data class Success(val students: List<Student>) : UiState()
    data class Error(val message: String) : UiState()
}

class StudentViewModel : ViewModel() {

    private val _uiState = MutableStateFlow<UiState>(UiState.Loading)
    val uiState: StateFlow<UiState> = _uiState

    fun fetchStudents() {
        viewModelScope.launch {
            try {
                val response = RetrofitInstance.api.getStudents()
                if (response.isSuccessful) {
                    response.body()?.let {
                        _uiState.value = UiState.Success(it)
                    } ?: run {
                        _uiState.value = UiState.Error("Respuesta vacía")
                    }
                } else {
                    _uiState.value = when (response.code()) {
                        404 -> UiState.Error("No encontrado")
                        500 -> UiState.Error("Error del servidor")
                        else -> UiState.Error("Error ${response.code()}")
                    }
                }
            } catch (e: Exception) {
                _uiState.value = UiState.Error("Sin conexión o error de red")
            }
        }
    }
}
