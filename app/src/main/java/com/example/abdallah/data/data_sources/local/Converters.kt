package com.example.abdallah.data.data_sources.local

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class Converters {


    @TypeConverter
    fun fromListString(value: List<String>?): String? {
        return Gson().toJson(value)
    }

    // Convert a JSON String back to a List<String>
    @TypeConverter
    fun toListString(value: String?): List<String>? {
        val listType = object : TypeToken<List<String>>() {}.type
        return Gson().fromJson(value, listType)
    }
}