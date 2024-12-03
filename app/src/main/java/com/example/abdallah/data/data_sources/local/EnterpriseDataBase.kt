package com.example.abdallah.data.data_sources.local
import androidx.room.TypeConverters
import androidx.room.Database
import androidx.room.RoomDatabase


@Database(
    entities = [EnterpriseEntity::class],
    version = 1
)


@TypeConverters(Converters::class)

abstract class EnterpriseDataBase: RoomDatabase() {
    abstract val enterpriseDao: EnterpriseDao
}
