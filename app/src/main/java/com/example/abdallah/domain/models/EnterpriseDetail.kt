package com.example.abdallah.domain.models

data class EnterpriseDetail(
    val name: String,
    val email: String,
    val location: String,
    val shortdescription: String,
    val description: String,
    val sujet: String,
    val Images: List<String>,
    val linkedIn: String,
    val pfeBook2023: String,
    val pfeBook2024: String,
    val website: String,
    val longitude: String,
    val latitude: String
)