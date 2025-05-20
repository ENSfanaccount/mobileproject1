package com.example.mobileproject1.examentercerparcial.view

import android.content.Context
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.mobileproject1.examentercerparcial.model.Student
import com.example.mobileproject1.examentercerparcial.viewmodel.StudentViewModel
import com.example.mobileproject1.examentercerparcial.viewmodel.UiState

@Composable
fun StudentListScreen(viewModel: StudentViewModel = viewModel()) {
    val uiState by viewModel.uiState.collectAsState()
    val context = LocalContext.current

    LaunchedEffect(Unit) {
        viewModel.fetchStudents()
    }

    when (uiState) {
        is UiState.Loading -> Text("Cargando...", modifier = Modifier.padding(16.dp))
        is UiState.Error -> Text(
            (uiState as UiState.Error).message,
            color = MaterialTheme.colorScheme.error,
            modifier = Modifier.padding(16.dp)
        )
        is UiState.Success -> {
            val students = (uiState as UiState.Success).students

            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                items(students) { student ->
                    Column(modifier = Modifier.fillMaxWidth()) {
                        val imageResId = getImageResourceId(student.imageName, context)

                        if (imageResId != 0) {
                            Image(
                                painter = painterResource(id = imageResId),
                                contentDescription = "Foto de ${student.name}",
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(150.dp)
                            )
                        } else {
                            Text(
                                "Imagen no encontrada: ${student.imageName}",
                                color = MaterialTheme.colorScheme.error
                            )
                        }

                        Text(text = student.name, style = MaterialTheme.typography.titleMedium)
                        Text(buildBoldLabelText("Matrícula:", student.studentId))
                        Text(buildBoldLabelText("Frase:", student.quote))
                    }
                }
            }
        }
    }
}

fun getImageResourceId(imageName: String, context: Context): Int {
    return context.resources.getIdentifier(
        imageName.substringBeforeLast(".").lowercase(),
        "drawable",
        context.packageName
    )
}

fun buildBoldLabelText(label: String, value: String): AnnotatedString {
    return buildAnnotatedString {
        pushStyle(SpanStyle(fontWeight = FontWeight.Bold))
        append("$label ")
        pop()
        append(value)
    }
}

