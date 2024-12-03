package com.example.abdallah.di

import android.content.Context
import androidx.room.Room
import com.example.abdallah.common.Constants
import com.example.abdallah.data.data_sources.local.EnterpriseDao
import com.example.abdallah.data.data_sources.local.EnterpriseDataBase
import com.example.abdallah.data.repositories.EnterpriseRepositoryImpl
import com.example.abdallah.data.data_sources.remote.EnterpriseApi
import com.example.abdallah.data.repositories.EnterpriseRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideApi():EnterpriseApi {
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(EnterpriseApi::class.java)
    }


    @Provides
    @Singleton
    fun provideEnterpriseDatabase(@ApplicationContext context: Context): EnterpriseDataBase {
        return Room.databaseBuilder(
            context.applicationContext,
            EnterpriseDataBase::class.java,
            "enterprise_database"
        ).build()
    }

    // Provide the EnterpriseDao instance from the AppDatabase
    @Provides
    @Singleton
    fun provideEnterpriseDao(database: EnterpriseDataBase): EnterpriseDao {
        return database.enterpriseDao
    }


    @Provides
    @Singleton
    fun provideEnterpriseRepository(api:EnterpriseApi , dao: EnterpriseDao):EnterpriseRepository {
        return EnterpriseRepositoryImpl(api,dao)
    }
}