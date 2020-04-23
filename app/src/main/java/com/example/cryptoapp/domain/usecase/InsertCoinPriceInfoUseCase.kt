package com.example.cryptoapp.domain.usecase

import com.example.cryptoapp.data.model.CoinPriceInfo
import com.example.cryptoapp.data.repository.CachedRateRepository
import io.reactivex.Completable

class InsertCoinPriceInfoUseCase {
    fun execute(dataList: List<CoinPriceInfo>): Completable =
        CachedRateRepository.insertCoinPriceInfo(dataList)
}
