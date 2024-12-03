package com.example.abdallah.data.data_sources.remote

import com.example.abdallah.common.Constants
import com.example.abdallah.data.data_sources.remote.dto.EnterpriseDetailDto
import com.example.abdallah.data.data_sources.remote.dto.EnterpriseDto
import retrofit2.http.GET
import retrofit2.http.Path

interface EnterpriseApi {
    @GET("api/getAllEnterprises")
    suspend fun getEntreprise():List<EnterpriseDto>

    @GET("api/getEnterpriseByName/{enterpriseId}")
    suspend fun getEnterpriseByName(@Path(Constants.PARAM_ENTERPRISE_ID) enterpriseId:String) : EnterpriseDetailDto
}