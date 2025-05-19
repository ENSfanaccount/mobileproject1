package com.example.mobileproject1.examentercerparcial.view


import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.mobileproject1.Students.views.StudentListScreen
import com.example.mobileproject1.examentercerparcial.viewmodel.StudentViewModel
import com.example.mobileproject1.examentercerparcial.viewmodel.UiState

@Composable
fun MainScreen() {
    val viewModel: StudentViewModel = viewModel()
    val uiState by viewModel.uiState.collectAsState()
    var showList by remember { mutableStateOf(false) }

    Scaffold(
        bottomBar = {
            BottomAppBar {
                Button(onClick = {
                    viewModel.fetchStudents()
                    showList = true
                }) {
                    Text("Mostrar Estudiantes")
                }
            }
        }
    ) { paddingValues ->
        Column(modifier = Modifier.padding(paddingValues)) {
            if (showList) {
                when (uiState) {
                    is UiState.Loading -> Text("Cargando...")
                    is UiState.Success -> StudentListScreen((uiState as UiState.Success).students)
                    is UiState.Error -> Text(
                        (uiState as UiState.Error).message,
                        color = MaterialTheme.colorScheme.error
                    )
                }
            }
        }
    }
}
