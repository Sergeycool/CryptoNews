package com.example.cryptoapp.domain.usecase

import com.example.cryptoapp.data.model.CoinPriceInfo
import com.example.cryptoapp.data.repository.CachedDataRepository
import io.reactivex.Flowable

class GetLastPriceListUseCase {
    fun execute(): Flowable<List<CoinPriceInfo>> =
        CachedDataRepository.getLastPriceList()
}