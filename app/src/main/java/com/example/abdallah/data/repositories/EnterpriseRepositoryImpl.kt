package com.example.abdallah.data.repositories


import android.util.Log
import com.example.abdallah.data.data_sources.local.EnterpriseDao
import com.example.abdallah.data.data_sources.local.toDto
import com.example.abdallah.data.data_sources.remote.EnterpriseApi
import com.example.abdallah.data.data_sources.remote.dto.EnterpriseDetailDto
import com.example.abdallah.data.data_sources.remote.dto.EnterpriseDto
import com.example.abdallah.data.data_sources.remote.dto.toEntity
import javax.inject.Inject

class EnterpriseRepositoryImpl @Inject constructor(
    private val api : EnterpriseApi,
    private  val dao: EnterpriseDao
) : EnterpriseRepository{



    override suspend fun getEnterprise(): List<EnterpriseDto> {
        return try {

            val localData = dao.getEnterprise()
            if (localData.isNotEmpty()) {
                Log.d("DB Debug", "Returning data from local DB")
                localData.map { it.toDto() }
            } else {
                Log.d("DB Debug", "Fetching data from API")
                val apiData = api.getEntreprise()
                dao.upsertEnterpriseList(apiData.map { it.toEntity() }) // Cache data locally
                apiData
            }
        } catch (e: Exception) {
            Log.e("Offline Support", "Error: ${e.message}")
            dao.getEnterprise().map { it.toDto() }
        }
    }


    override suspend fun getEnterpriseByName(enterpriseId: String): EnterpriseDetailDto {
        return try {
            val enterpriseDetail = api.getEnterpriseByName(enterpriseId)

            enterpriseDetail
        } catch (e: Exception) {
            val localEnterprise = dao.getEnterpriseByName(enterpriseId)
            localEnterprise
        }
    }

}
