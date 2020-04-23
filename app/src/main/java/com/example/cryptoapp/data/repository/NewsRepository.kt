package com.example.cryptoapp.data.repository

import com.example.cryptoapp.data.database.AppDatabase
import com.example.cryptoapp.data.model.GetNewsResponse
import com.example.cryptoapp.data.model.News
import com.example.cryptoapp.data.remote.RetrofitServiceHolder
import com.example.cryptoapp.presentation.App
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Single
import retrofit2.Response

object NewsRepository {
    private val db = AppDatabase.getInstance(App.context)

    fun getAllNews(lang: String): Single<Response<GetNewsResponse>> =
        RetrofitServiceHolder.apiService.getNews(lang)

    fun getCachedNews(): Flowable<List<News>> = db.newsDao().getNewsList()

    fun insertNews(news: List<News>): Completable = db.newsDao().insertNewsList(news)
}