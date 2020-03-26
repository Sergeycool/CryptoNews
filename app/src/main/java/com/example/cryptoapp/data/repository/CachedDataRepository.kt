package com.example.cryptoapp.data.repository

import com.example.cryptoapp.data.database.AppDatabase
import com.example.cryptoapp.data.model.CoinPriceInfo
import com.example.cryptoapp.presentation.App
import io.reactivex.Flowable

object CachedDataRepository {
    private val db = AppDatabase.getInstance(App.context)

    fun getDetailInfoForCurrency(fromSymbol: String): Flowable<CoinPriceInfo>{
        return db.coinPriceInfoDao().getPriceInfoAboutCoin(fromSymbol)
    }
}
