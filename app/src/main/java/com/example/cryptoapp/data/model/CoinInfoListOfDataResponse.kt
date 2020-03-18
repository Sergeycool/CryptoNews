package com.example.cryptoapp.data.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class CoinInfoListOfDataResponse (
    @SerializedName("Data")
    @Expose
    val data: List<Datum>? = null
)
