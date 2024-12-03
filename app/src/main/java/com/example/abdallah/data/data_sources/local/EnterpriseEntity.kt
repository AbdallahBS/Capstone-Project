package com.example.abdallah.data.data_sources.local

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.abdallah.data.data_sources.remote.dto.EnterpriseDto

@Entity
data class EnterpriseEntity (
    @PrimaryKey
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
    val longitude: String,
    val latitude: String
)
fun EnterpriseEntity.toDto(): EnterpriseDto {
    return EnterpriseDto(
        name = name,
        email = email,
        location = location,
        shortdescription = shortdescription,
        description = description,
        sujet = sujet,
        Images = Images,
        linkDin = linkDin,
        pfeBook2023 = pfeBook2023,
        pfeBook2024 = pfeBook2024,
        web = web,
        long = longitude,
        lat = latitude
    )
}