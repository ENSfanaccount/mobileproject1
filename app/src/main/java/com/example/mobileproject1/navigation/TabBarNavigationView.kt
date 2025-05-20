package com.example.mobileproject1.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.*
import androidx.navigation.compose.rememberNavController
import com.example.mobileproject1.IMC.views.BmiScreen

import com.example.mobileproject1.Students.views.StudentListScreen2
import com.example.mobileproject1.ids.IdsView
import com.example.mobileproject1.firstpartial.FirstPartialView
import com.example.mobileproject1.location.ui.LocationListScreen
import com.example.mobileproject1.secondpartial.SecondPartialView
import com.example.mobileproject1.sum.views.SumBox
import com.example.mobileproject1.temperatura.views.TemperatureScreen
import com.example.mobileproject1.thirdpartial.ThirdPartialScreen




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

            composable(ScreenNavigation.SUM.route) { SumBox() }
            composable(ScreenNavigation.IMC.route) { BmiScreen() }
            composable(ScreenNavigation.temperatura.route) { TemperatureScreen() }
            composable(ScreenNavigation.Students2.route) { StudentListScreen2() }
            composable(ScreenNavigation.raquet.route) { LocationListScreen() }
        }
    }
}