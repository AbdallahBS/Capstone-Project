package com.example.abdallah.domain.use_cases

import android.util.Log
import com.example.abdallah.common.Resource
import com.example.abdallah.data.data_sources.remote.dto.toEntreprise
import com.example.abdallah.data.repositories.EnterpriseRepository
import com.example.abdallah.domain.models.Enterprise
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetEnterprisesUseCase @Inject constructor(
    private val repository: EnterpriseRepository
) {
    operator fun invoke(): Flow<Resource<List<Enterprise>>> = flow {
        try {
            Log.d("hmmm","debug");
            emit(Resource.Loading())
            val enterprise = repository.getEnterprise().map {it.toEntreprise()}
            Log.d("entre","${enterprise}");

            emit(Resource.Success(enterprise))
        } catch (e: HttpException) {
            emit(Resource.Error(e.localizedMessage ?: "Http error"))
        } catch (e: IOException) {
            emit(Resource.Error("Check your internet connection"))
        }
    }
}