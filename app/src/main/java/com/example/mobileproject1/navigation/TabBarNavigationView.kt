package com.example.mobileproject1.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.*
import androidx.navigation.navArgument
import com.example.mobileproject1.IMC.views.BmiScreen
import com.example.mobileproject1.Students.views.StudentListScreen
import com.example.mobileproject1.examenfinal.RestaurantViewModel.RestaurantsViewModel
import com.example.mobileproject1.examenfinal.model.Restaurant
import com.example.mobileproject1.examenfinal.view.RestaurantDetailsView
import com.example.mobileproject1.examenfinal.view.RestaurantsScreen
import com.example.mobileproject1.examentercerparcial.view.StudentsListScreen
import com.example.mobileproject1.ids.IdsView
import com.example.mobileproject1.firstpartial.FirstPartialView
import com.example.mobileproject1.secondpartial.SecondPartialView
import com.example.mobileproject1.sum.views.SumBox
import com.example.mobileproject1.temperatura.views.TemperatureScreen
import com.example.mobileproject1.thirdpartial.ThirdPartialScreen
import com.google.gson.Gson
import java.net.URLDecoder
import java.net.URLEncoder
import java.nio.charset.StandardCharsets

@Composable
fun TabBarNavigationView(navController: NavHostController = rememberNavController()) {
    val items = listOf(
        ScreenNavigation.Ids,
        ScreenNavigation.FirstPartial,
        ScreenNavigation.SecondPartial,
        ScreenNavigation.ThirdPartial

    )

    Scaffold(
        bottomBar = {
            NavigationBar {
                val navBackStackEntry by navController.currentBackStackEntryAsState()
                val currentRoute = navBackStackEntry?.destination?.route
                items.forEach { screen ->
                    NavigationBarItem(
                        icon = { Icon(screen.icon, contentDescription = screen.label) },
                        label = { Text(screen.label) },
                        selected = currentRoute == screen.route,
                        onClick = {
                            if (currentRoute != screen.route) {
                                navController.navigate(screen.route)
                            }
                        }
                    )
                }
            }
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = ScreenNavigation.Ids.route,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(ScreenNavigation.Ids.route) { IdsView(navController) }
            composable(ScreenNavigation.FirstPartial.route) { FirstPartialView() }
            composable(ScreenNavigation.SecondPartial.route) { SecondPartialView() }
            composable(ScreenNavigation.ThirdPartial.route) { ThirdPartialScreen(navController) }
            composable(ScreenNavigation.Students.route) { StudentsListScreen() }
            composable(ScreenNavigation.SUM.route) { SumBox() }
            composable(ScreenNavigation.IMC.route) { BmiScreen() }
            composable(ScreenNavigation.temperatura.route) { TemperatureScreen() }
            composable(ScreenNavigation.Restaurants.route) { RestaurantsScreen(navController) }

            // ✅ Add this missing composable
            composable(ScreenNavigation.RestaurantList.route) {
                RestaurantsScreen(navController)
            }

            composable(
                route = "restaurantDetail/{restaurantName}",
                arguments = listOf(navArgument("restaurantName") { type = NavType.StringType })
            ) { backStackEntry ->
                val restaurantNameEncoded =
                    backStackEntry.arguments?.getString("restaurantName") ?: ""
                val restaurantName =
                    URLDecoder.decode(restaurantNameEncoded, StandardCharsets.UTF_8.name())
                val viewModel: RestaurantsViewModel = viewModel()
                val restaurantList = viewModel.restaurants.collectAsState().value
                val restaurant = restaurantList.find { it.name == restaurantName }

                restaurant?.let {
                    RestaurantDetailsView(it)
                } ?: run {
                    Text(text = "Restaurante no encontrado", modifier = Modifier.padding(16.dp))
                }
            }
        }
    }
}

