package com.example.mobileproject1.examenfinal.view

import android.content.Context
import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.mobileproject1.examenfinal.RestaurantViewModel.RestaurantsViewModel

// ✅ Fixed utility function (not @Composable)
fun getDrawableIdOrDefault(context: Context, imageName: String): Int {
    val resId = context.resources.getIdentifier(imageName, "drawable", context.packageName)
    return if (resId != 0) resId else android.R.drawable.stat_notify_error
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RestaurantsScreen(
    navController: NavHostController,
    viewModel: RestaurantsViewModel = viewModel()
) {
    val restaurants = viewModel.restaurants.collectAsState()
    val errorMessage = viewModel.errorMessage.collectAsState()
    val context = LocalContext.current

    Scaffold(
        topBar = { TopAppBar(title = { Text("Lista de Restaurantes") }) }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(8.dp)
        ) {
            errorMessage.value?.let { msg ->
                Text(text = msg, color = MaterialTheme.colorScheme.error)
                Spacer(modifier = Modifier.height(8.dp))
            }

            LazyColumn(verticalArrangement = Arrangement.spacedBy(12.dp)) {
                items(restaurants.value) { restaurant ->
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable {
                                navController.navigate(
                                    "restaurantDetail/${Uri.encode(restaurant.name)}"
                                )
                            },
                        shape = RoundedCornerShape(12.dp),
                        elevation = CardDefaults.cardElevation(8.dp)
                    ) {
                        Row(
                            modifier = Modifier.padding(12.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            val imageRes = context.resources.getIdentifier(
                                restaurant.imgName.lowercase()
                                    .replace(Regex("[^a-z0-9_]"), ""),
                                "drawable",
                                context.packageName
                            )
                            if (imageRes != 0) {
                                Image(
                                    painter = painterResource(id = imageRes),
                                    contentDescription = restaurant.name,
                                    modifier = Modifier
                                        .size(90.dp)
                                        .clip(RoundedCornerShape(10.dp)),
                                    contentScale = ContentScale.Crop
                                )
                            } else {
                                Box(
                                    modifier = Modifier
                                        .size(90.dp)
                                        .background(Color.Gray)
                                        .clip(RoundedCornerShape(10.dp)),
                                    contentAlignment = Alignment.Center
                                ) {
                                    Text("No imagen", color = Color.White, fontSize = 12.sp)
                                }
                            }

                            Spacer(modifier = Modifier.width(16.dp))

                            Column(modifier = Modifier.weight(1f)) {
                                Text(
                                    text = restaurant.name,
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 18.sp
                                )
                                Spacer(modifier = Modifier.height(4.dp))

                                // Rating con estrellas (solo ícono repetido)
                                Row(verticalAlignment = Alignment.CenterVertically) {
                                    repeat(5) { index ->
                                        Icon(
                                            imageVector = Icons.Filled.Star,
                                            contentDescription = null,
                                            tint = if (index < restaurant.rating.toInt()) Color.Yellow else Color.LightGray,
                                            modifier = Modifier.size(16.dp)
                                        )
                                    }
                                    Spacer(modifier = Modifier.width(8.dp))
                                    Text(
                                        text = "(${restaurant.rating})",
                                        fontSize = 12.sp,
                                        color = Color.Gray
                                    )
                                }

                                Spacer(modifier = Modifier.height(4.dp))

                                Text(
                                    text = "Costo envío: MX ${restaurant.fee}",
                                    fontSize = 14.sp
                                )
                                Text(
                                    text = "Tiempo entrega: ${restaurant.delivery}",
                                    fontSize = 14.sp
                                )
                            }

                            Icon(
                                imageVector = if (restaurant.isFavorite) Icons.Filled.Favorite else Icons.Outlined.FavoriteBorder,
                                contentDescription = "Favorito",
                                tint = if (restaurant.isFavorite) Color.Red else Color.Gray,
                                modifier = Modifier.size(24.dp)
                            )
                        }
                    }
                }
            }
        }
    }
}
