package com.example.mobileproject1.examenfinal.model

import com.google.android.gms.maps.model.LatLng

data class Restaurant(
    val name: String,
    val imgName: String,
    val schedule: String,
    val phone: String,
    val rating: Double,
    val delivery: String,
    val isFavorite: Boolean,
    val fee: String,
    val webSite: String,
    val latitude: String,
    val longitude: String
) {
    fun getLatLng(): LatLng? {
        val lat = latitude.toDoubleOrNull()
        val lng = longitude.toDoubleOrNull()
        return if (lat != null && lng != null) LatLng(lat, lng) else null
    }
}
