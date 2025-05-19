package com.example.mobileproject1.location.repository
import com.example.mobileproject1.location.model.Location
import com.example.mobileproject1.location.network.LocationApiService

class LocationRepository(private val apiService: LocationApiService) {
    suspend fun fetchLocations(): List<Location> {
        return apiService.getLocations()
    }
}
