package com.example.cryptoapp.data.remote.api

import com.example.cryptoapp.data.model.CoinInfoListOfDataResponse
import com.example.cryptoapp.data.model.CoinPriceInfoRawData
import com.example.cryptoapp.data.model.GetNewsResponse
import io.reactivex.Single
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface CryptoNewsApiService {
    companion object {
        private const val QUERY_PARAM_API_KEY = "api_key"
        private const val QUERY_PARAM_LIMIT = "limit"
        private const val QUERY_PARAM_TO_SYMBOL = "tsym"
        private const val QUERY_PARAM_TO_SYMBOLS = "tsyms"
        private const val QUERY_PARAM_FROM_SYMBOLS = "fsyms"
        private const val QUERY_PARAM_LANG = "lang"
    }

    @GET("top/totalvolfull")
    fun getTopCoinsInfo(
        @Query(QUERY_PARAM_API_KEY) apiKey: String,
        @Query(QUERY_PARAM_LIMIT) limit: Int,
        @Query(QUERY_PARAM_TO_SYMBOL) tSym: String
    ): Single<Response<CoinInfoListOfDataResponse>>

    @GET("pricemultifull")
    fun getFullPriceList(
        @Query(QUERY_PARAM_API_KEY) apiKey: String,
        @Query(QUERY_PARAM_FROM_SYMBOLS) fSyms: String,
        @Query(QUERY_PARAM_TO_SYMBOLS) tSyms: String
    ): Single<CoinPriceInfoRawData>

    @GET("v2/news/")
    fun getNews(
        @Query(QUERY_PARAM_LANG) language: String
    ): Single<Response<GetNewsResponse>>

}
