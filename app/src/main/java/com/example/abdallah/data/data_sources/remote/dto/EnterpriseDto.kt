package com.example.abdallah.data.data_sources.remote.dto

import com.example.abdallah.data.data_sources.local.EnterpriseEntity
import com.example.abdallah.domain.models.Enterprise

data class EnterpriseDto(
    val name: String,
    val email: String,
    val location: String,
    val shortdescription: String,
    val description: String,
    val sujet: String,
    val Images: List<String>,
    val linkDin: String,
    val pfeBook2023: String,
    val pfeBook2024: String,
    val web: String,
    val long: String,
    val lat: String
)

fun EnterpriseDto.toEntreprise(): Enterprise {
    return Enterprise(
        name = name,
        email = email,
        location = location,
        shortDescription = shortdescription,
        description = description,
        images = Images,
        linkDin = linkDin,
        pfeBook2023 = pfeBook2023,
        pfeBook2024 = pfeBook2024,
        web = web,
        longitude = long.toDoubleOrNull() ?: 0.0,
        latitude = lat.toDoubleOrNull() ?: 0.0
    )
}

fun EnterpriseDto.toEntity(): EnterpriseEntity {
    return EnterpriseEntity(
        name = name,
        email = email,
        location = location,
        shortdescription = shortdescription,
        description = description,
        sujet = sujet ?: "No Sujet available",
        Images = Images,
        linkDin = linkDin,
        pfeBook2023 = pfeBook2023,
        pfeBook2024 = pfeBook2024,
        web = web,
        longitude = long,
        latitude = lat
    )
}