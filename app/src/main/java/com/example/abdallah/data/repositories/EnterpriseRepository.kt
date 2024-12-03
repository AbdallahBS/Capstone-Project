package com.example.abdallah.data.repositories

import com.example.abdallah.data.data_sources.remote.dto.EnterpriseDetailDto
import com.example.abdallah.data.data_sources.remote.dto.EnterpriseDto


interface EnterpriseRepository {

    suspend fun getEnterprise():List<EnterpriseDto>
    suspend fun getEnterpriseByName(enterpriseId:String): EnterpriseDetailDto
}