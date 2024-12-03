package com.example.abdallah.data.data_sources.remote.dto

import com.example.abdallah.domain.models.EnterpriseDetail

data class EnterpriseDetailDto(
    val name: String,
    val email: String,
    val location: String,
    val shortdescription: String,
    val description: String,
    val sujet: String?,
    val Images: List<String>,
    val linkedIn: String?,
    val pfeBook2023: String?,
    val pfeBook2024: String?,
    val website: String?,
    val longitude: String?,
    val latitude: String?
)

fun EnterpriseDetailDto.toEnterpriseDetail(): EnterpriseDetail {
    return EnterpriseDetail(
        name = name,
        email = email,
        location = location,
        shortdescription = shortdescription,
        description = description,
        sujet = sujet.orEmpty(),
        Images = Images,
        linkedIn = linkedIn.orEmpty(),
        pfeBook2023 = pfeBook2023.orEmpty(),
        pfeBook2024 = pfeBook2024.orEmpty(),
        website = website.orEmpty(),
        longitude = longitude.orEmpty(),
        latitude = latitude.orEmpty()
    )
}