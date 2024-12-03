package com.example.abdallah.domain.use_cases

import com.example.abdallah.common.Resource
import com.example.abdallah.data.data_sources.remote.dto.toEnterpriseDetail
import com.example.abdallah.data.repositories.EnterpriseRepository
import com.example.abdallah.domain.models.EnterpriseDetail
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject


class GetEnterpriseDetailUseCase @Inject constructor(
    private val repository: EnterpriseRepository
) {
    operator fun invoke(coinId:String): Flow<Resource<EnterpriseDetail>> = flow {
        try {
            emit(Resource.Loading())
            val enterpriseDetail = repository.getEnterpriseByName(coinId).toEnterpriseDetail()
            emit(Resource.Success(enterpriseDetail))
        } catch (e: HttpException) {
            emit(Resource.Error(e.localizedMessage ?: "Http error"))
        } catch (e: IOException) {
            emit(Resource.Error("Check your internet connection"))
        }
    }
}
