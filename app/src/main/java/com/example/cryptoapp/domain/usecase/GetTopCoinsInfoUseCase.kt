package com.example.cryptoapp.domain.usecase

import com.example.cryptoapp.data.model.CoinPriceInfo
import com.example.cryptoapp.data.model.CoinPriceInfoRawData
import com.example.cryptoapp.data.repository.RateRepository
import com.example.cryptoapp.domain.DomainConstants.CURRENCY
import com.example.cryptoapp.toolchain.rxjava.RxMessage
import com.example.cryptoapp.toolchain.rxjava.RxResponseException
import com.google.gson.Gson
import io.reactivex.Observable
import io.reactivex.Single

class GetTopCoinsInfoUseCase {
    fun execute(
        apiKey: String = "",
        limit: Int,
        tSym: String = CURRENCY
    ): Observable<RxMessage<List<CoinPriceInfo>>> {

        val startSingle: Single<RxMessage<List<CoinPriceInfo>>> =
            Single.just(RxMessage.onStart())

        val requestSingle: Single<RxMessage<List<CoinPriceInfo>>> = RateRepository
            .getTopCoinsInfo(apiKey, limit, tSym)
            .map {
                if (it.isSuccessful)
                    RxMessage.onNext(it.body())
                else
                    throw RxResponseException(it.code(), it.message())

                it.body()?.data?.map { it.coinInfo?.name }?.joinToString(",")
            }
            .flatMap { RateRepository.getFullPriceList(fSyms = it, tSyms = CURRENCY) }
            .map {
                RxMessage.onNextLast(getPriceListFromRawData(it))
            }
            .onErrorReturn {
                RxMessage.onError(it)
            }

        return Observable.concat(startSingle.toObservable(), requestSingle.toObservable())
    }

    private fun getPriceListFromRawData(coinPriceInfoRawData: CoinPriceInfoRawData?): List<CoinPriceInfo> {
        val result = ArrayList<CoinPriceInfo>()
        val jsonObject = coinPriceInfoRawData?.coinPriceInfoJsonObject ?: return result
        val coinKeySet = jsonObject.keySet()
        for (coinKey in coinKeySet) {
            val currencyJson = jsonObject.getAsJsonObject(coinKey)
            val currencyKeySet = currencyJson.keySet()
            for (currencyKey in currencyKeySet) {
                val priceInfo = Gson().fromJson(
                    currencyJson.getAsJsonObject(currencyKey),
                    CoinPriceInfo::class.java
                )
                result.add(priceInfo)
            }
        }
        return result
    }
}
