package com.example.cryptoapp.toolchain.network

import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.net.ConnectException

object RetrofitUtil {
    private val HTTP_LOG_LEVEL = HttpLoggingInterceptor.Level.BODY

    val SHARED_OK_HTTP_CLIENT_BUILDER: OkHttpClient.Builder = OkHttpClient.Builder()
        .addInterceptor(HttpLoggingInterceptor().setLevel(HTTP_LOG_LEVEL))

    private val RETROFIT_BUILDER: Retrofit.Builder = Retrofit.Builder()
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .addConverterFactory(GsonConverterFactory.create())

    fun createRetrofit(baseUrl: String,
                       okHttpClient: OkHttpClient): Retrofit {
        return RETROFIT_BUILDER
            .baseUrl(baseUrl)
            .client(okHttpClient)
            .build()
    }

    fun getNetworkConnectionInterceptor(): Interceptor {
        return Interceptor { chain ->
            if (NetworkUtil.isNetworkAvailable())
                chain.proceed(chain.request())
             else
                throw ConnectException()
        }
    }

}