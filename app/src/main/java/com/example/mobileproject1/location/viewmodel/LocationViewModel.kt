package com.example.mobileproject1.location.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mobileproject1.location.model.Location
import com.example.mobileproject1.location.network.LocationApiService
import com.example.mobileproject1.location.repository.LocationRepository
import kotlinx.coroutines.launch
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class LocationViewModel : ViewModel() {

    private val apiService = LocationApiService.create()
    private val repository = LocationRepository(apiService)

    private val _locations = MutableStateFlow<List<Location>>(emptyList())
    val locations = _locations.asStateFlow()

    init {
        loadLocations()
    }

    private fun loadLocations() {
        viewModelScope.launch {
            try {
                _locations.value = repository.fetchLocations()
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}