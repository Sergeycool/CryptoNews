package com.example.cryptoapp.data.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

abstract class BaseApiResponse<D> {
    @SerializedName("Message")
    @Expose
    val message: String? = null

    @SerializedName("Data")
    @Expose
    var data: D? = null
}
