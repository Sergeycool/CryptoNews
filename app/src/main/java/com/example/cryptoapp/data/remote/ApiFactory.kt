package com.example.cryptoapp.data.remote

import com.example.cryptoapp.data.DataConstants.Companion.CRYPTO_NEWS_BASE_URL
import com.example.cryptoapp.data.remote.api.ApiService
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

object ApiFactory {

    private val retrofit = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .baseUrl(CRYPTO_NEWS_BASE_URL)
        .build()

    val apiService: ApiService = retrofit.create(ApiService::class.java)
}
