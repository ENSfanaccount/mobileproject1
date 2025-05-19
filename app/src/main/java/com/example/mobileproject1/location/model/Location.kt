package com.example.mobileproject1.location.model

data class Location(
    val id: String,
    val name: String,
    val address: String,
    val latitude: Double,
    val longitude: Double,
    val imageUrl: String
)