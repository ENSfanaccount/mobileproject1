package com.example.mobileproject1.examenfinal.view

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Language
import androidx.compose.material.icons.filled.Map
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mobileproject1.examenfinal.model.Restaurant
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RestaurantDetailsView(restaurant: Restaurant) {
    val context = LocalContext.current

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(restaurant.name) }
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState()) // ← Scroll añadido
                .padding(padding)
        ) {
            // Imagen del restaurante
            val imageResId = context.resources.getIdentifier(
                restaurant.imgName.lowercase().replace(Regex("[^a-z0-9_]"), ""),
                "drawable",
                context.packageName
            )

            if (imageResId != 0) {
                Box(modifier = Modifier.height(280.dp)) {
                    Image(
                        painter = painterResource(id = imageResId),
                        contentDescription = restaurant.name,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(280.dp),
                        contentScale = ContentScale.Crop
                    )
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(280.dp)
                            .background(
                                Brush.verticalGradient(
                                    colors = listOf(
                                        Color.Transparent,
                                        Color.Black.copy(alpha = 0.7f)
                                    ),
                                    startY = 0f,
                                    endY = 280f
                                )
                            )
                    )
                    Text(
                        text = restaurant.name,
                        modifier = Modifier
                            .align(Alignment.BottomStart)
                            .padding(16.dp),
                        color = Color.White,
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
            } else {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(280.dp)
                        .background(Color.Gray),
                    contentAlignment = Alignment.Center
                ) {
                    Text("Imagen no disponible", color = Color.White)
                }
            }

            Spacer(modifier = Modifier.height(16.dp))


            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                shape = RoundedCornerShape(12.dp),
                elevation = CardDefaults.cardElevation(4.dp)
            ) {
                Column(modifier = Modifier.padding(12.dp)) {
                    Text("Horario: ${restaurant.schedule}", fontSize = 14.sp)
                    Spacer(modifier = Modifier.height(4.dp))
                    Text("Calificación: ${restaurant.rating}", fontSize = 14.sp)
                    Spacer(modifier = Modifier.height(4.dp))
                    Text("Costo envío: MX ${restaurant.fee}", fontSize = 14.sp)
                    Spacer(modifier = Modifier.height(4.dp))
                    Text("Tiempo entrega: ${restaurant.delivery}", fontSize = 14.sp)
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = "Teléfono: ${restaurant.phone}",
                        fontSize = 14.sp,
                        color = MaterialTheme.colorScheme.primary,
                        modifier = Modifier.clickable {
                            val intent = Intent(Intent.ACTION_DIAL).apply {
                                data = Uri.parse("tel:${restaurant.phone}")
                            }
                            context.startActivity(intent)
                        }
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = "Sitio web: ${restaurant.webSite}",
                        fontSize = 14.sp,
                        color = MaterialTheme.colorScheme.primary,
                        modifier = Modifier.clickable {
                            val intent = Intent(Intent.ACTION_VIEW).apply {
                                data = Uri.parse(restaurant.webSite)
                            }
                            context.startActivity(intent)
                        }
                    )
                }
            }
            Spacer(modifier = Modifier.height(16.dp))


            val latitude = restaurant.latitude.toDoubleOrNull() ?: 0.0
            val longitude = restaurant.longitude.toDoubleOrNull() ?: 0.0
            val cameraPositionState = rememberCameraPositionState {
                position = CameraPosition.fromLatLngZoom(LatLng(latitude, longitude), 15f)
            }

            GoogleMap(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(250.dp)
                    .padding(horizontal = 16.dp),
                cameraPositionState = cameraPositionState
            ) {
                Marker(
                    state = MarkerState(position = LatLng(latitude, longitude)),
                    title = restaurant.name
                )
            }

        }
    }
}
