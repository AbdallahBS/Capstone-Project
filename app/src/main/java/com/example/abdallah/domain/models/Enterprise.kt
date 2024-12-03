package com.example.abdallah.domain.models

data class Enterprise(
    val name: String,
    val email: String,
    val location: String,
    val shortDescription: String,
    val description: String,
    val images: List<String>,
    val linkDin: String,
    val pfeBook2023: String,
    val pfeBook2024: String,
    val web: String,
    val longitude: Double,
    val latitude: Double
) {


}
