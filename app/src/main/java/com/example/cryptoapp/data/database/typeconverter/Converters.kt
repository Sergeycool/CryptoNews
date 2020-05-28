package com.example.cryptoapp.data.database.typeconverter

import androidx.room.TypeConverter
import com.example.cryptoapp.data.model.NewsSourceInfo
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

object Converters {
    @TypeConverter
    @JvmStatic
    fun fromNewsSourceInfo(value: NewsSourceInfo): String {
        val gson = Gson()
        val type = object : TypeToken<NewsSourceInfo>() {}.type
        return gson.toJson(value, type)
    }

    @TypeConverter
    @JvmStatic
    fun toNewsSourceInfo(value: String): NewsSourceInfo {
        val gson = Gson()
        val type = object : TypeToken<NewsSourceInfo>() {}.type
        return gson.fromJson(value, type)
    }

}
