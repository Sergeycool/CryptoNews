package com.example.cryptoapp.domain.usecase

import com.example.cryptoapp.data.model.CoinPriceInfo
import com.example.cryptoapp.data.repository.CachedRateRepository
import io.reactivex.Flowable

class GetLastPriceListUseCase {
    fun execute(): Flowable<List<CoinPriceInfo>> =
        CachedRateRepository.getLastPriceList()
}