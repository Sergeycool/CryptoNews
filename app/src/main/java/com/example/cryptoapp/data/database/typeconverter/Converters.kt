package com.example.cryptoapp.data.database.typeconverter

import androidx.room.TypeConverter
import com.example.cryptoapp.data.model.NewsSourceInfo

object Converters {
    @TypeConverter
    @JvmStatic
    fun fromNewsSourceInfo(value: NewsSourceInfo): String? {

        //TODO replace hardcoded order with gson
//        val info = Gson().fromJson(
//            value.getAsJsonObject(currencyKey),
//            CoinPriceInfo::class.java
//        )

        // do not change order
        val dataList = listOf(value.sourceName, value.language, value.sourceImage)
        return dataList.joinToString { "," }
    }

    @TypeConverter
    @JvmStatic
    fun toNewsSourceInfo(data: String?): NewsSourceInfo? {
        val array = data?.split(",")
        return NewsSourceInfo(array?.get(0), array?.get(1), array?.get(2));
    }




}
