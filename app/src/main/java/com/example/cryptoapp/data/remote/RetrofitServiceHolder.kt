package com.example.cryptoapp.data.remote

import com.example.cryptoapp.data.DataConstants.CRYPTO_NEWS_BASE_URL
import com.example.cryptoapp.data.DataConstants.OK_HTTP_CLIENT_CONNECT_TIMEOUT
import com.example.cryptoapp.data.DataConstants.OK_HTTP_CLIENT_READ_TIMEOUT
import com.example.cryptoapp.data.DataConstants.OK_HTTP_CLIENT_WRITE_TIMEOUT
import com.example.cryptoapp.data.remote.api.CryptoNewsApiService
import com.example.cryptoapp.toolchain.network.RetrofitUtil
import java.util.concurrent.TimeUnit

object RetrofitServiceHolder {

    private val retrofit = RetrofitUtil.createRetrofit(CRYPTO_NEWS_BASE_URL, cryptoNewsOkHttpClient())

    private fun cryptoNewsOkHttpClient() = RetrofitUtil.SHARED_OK_HTTP_CLIENT_BUILDER
        .connectTimeout(OK_HTTP_CLIENT_CONNECT_TIMEOUT, TimeUnit.SECONDS)
        .readTimeout(OK_HTTP_CLIENT_READ_TIMEOUT, TimeUnit.SECONDS)
        .writeTimeout(OK_HTTP_CLIENT_WRITE_TIMEOUT, TimeUnit.SECONDS)
        .build()

    val apiService: CryptoNewsApiService = retrofit.create(CryptoNewsApiService::class.java)
}
