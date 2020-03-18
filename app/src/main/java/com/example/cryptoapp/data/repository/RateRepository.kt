package com.example.cryptoapp.data.repository

import androidx.annotation.NonNull
import com.example.cryptoapp.data.model.CoinInfoListOfDataResponse
import com.example.cryptoapp.data.model.CoinPriceInfoRawData
import com.example.cryptoapp.data.remote.RetrofitServiceHolder
import io.reactivex.Single
import retrofit2.Response

object RateRepository {

    @NonNull
    fun getTopCoinsInfo(apiKey: String = "", limit: Int, tSym: String
    ): Single<Response<CoinInfoListOfDataResponse>> =
        RetrofitServiceHolder.apiService.getTopCoinsInfo(apiKey, limit, tSym)

    @NonNull
    fun getFullPriceList(apiKey: String = "", fSyms: String, tSyms: String
    ): Single<CoinPriceInfoRawData> =
        RetrofitServiceHolder.apiService.getFullPriceList(apiKey, fSyms, tSyms)


}