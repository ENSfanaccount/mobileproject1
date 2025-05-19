package com.example.mobileproject1.examentercerparcial.view

import android.content.Context
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.mobileproject1.examentercerparcial.model.Student

fun getImageResourceId(imageName: String, context: Context): Int {
    return context.resources.getIdentifier(
        imageName.substringBeforeLast(".").lowercase(),
        "drawable",
        context.packageName
    )
}

@Composable
fun StudentListScreen(students: List<Student>) {
    val context = LocalContext.current

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        items(students) { student ->
            Column(modifier = Modifier.padding(vertical = 8.dp)) {

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

                Text(student.name)
                Text("Matrícula: ${student.studentId}")
                Text("Frase: ${student.quote}")
            }
        }
    }
}
