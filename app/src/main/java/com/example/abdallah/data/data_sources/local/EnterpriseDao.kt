package com.example.abdallah.data.data_sources.local

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import com.example.abdallah.data.data_sources.remote.dto.EnterpriseDetailDto

@Dao
interface EnterpriseDao {

    @Upsert
    suspend fun upsertEnterpriseList(enterpriseList: List<EnterpriseEntity>)

    @Query("SELECT * FROM EnterpriseEntity")
    suspend fun getEnterprise(): List<EnterpriseEntity>

    @Query("SELECT * FROM EnterpriseEntity WHERE name = :name")
    suspend fun getEnterpriseByName(name: String): EnterpriseDetailDto
}