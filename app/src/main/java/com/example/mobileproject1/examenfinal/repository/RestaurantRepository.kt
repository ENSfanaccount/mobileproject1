package com.example.mobileproject1.examenfinal.repository


import com.example.mobileproject1.examenfinal.model.Restaurant
import com.example.mobileproject1.examenfinal.network.RestaurantsApiService

class RestaurantsRepository(private val apiService: RestaurantsApiService) {
    suspend fun fetchRestaurants(): List<Restaurant> = apiService.getRestaurants()
}
