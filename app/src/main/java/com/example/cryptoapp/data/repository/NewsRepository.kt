package com.example.cryptoapp.data.repository

import com.example.cryptoapp.data.model.GetNewsResponse
import com.example.cryptoapp.data.remote.RetrofitServiceHolder
import io.reactivex.Single
import retrofit2.Response

object NewsRepository {
    fun getAllNews(lang: String): Single<Response<GetNewsResponse>> =
        RetrofitServiceHolder.apiService.getNews(lang)
}