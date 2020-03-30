package com.example.cryptoapp.presentation.viewmodel.home

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.cryptoapp.data.model.CoinPriceInfo
import com.example.cryptoapp.domain.usecase.GetLastPriceListUseCase
import com.example.cryptoapp.domain.usecase.GetTopCoinsInfoUseCase
import com.example.cryptoapp.domain.usecase.InsertCoinPriceInfoUseCase
import com.example.cryptoapp.toolchain.mvvmbase.BaseViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import java.util.concurrent.TimeUnit

class CurrencyListViewModel : BaseViewModel() {
    val coinDataList: MutableLiveData<List<CoinPriceInfo>> = MutableLiveData()

    init {
        loadData()
    }

    private fun loadData() {
        disposables.add(
            GetTopCoinsInfoUseCase().execute(limit = 10)
                .delaySubscription(10, TimeUnit.SECONDS)
                .repeat()
                .retry()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    if (it.isCompleteWithoutError()) {
                        it.result?.let { priceList ->
                            coinDataList.value = priceList
                            saveUpdatedData(priceList)
                        }
                    } else {
                        // get cached data after
                        getLastPriceList()
                    }
                }, {
                    Log.d(TAG, "Failure: ${it.message}")
                })
        )
    }

    private fun saveUpdatedData(priceListInfo: List<CoinPriceInfo>) {
        InsertCoinPriceInfoUseCase().execute(priceListInfo)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe()
    }

    private fun getLastPriceList() {
        disposables.add(
            GetLastPriceListUseCase().execute()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ coinDataList.value = it },
                    { Log.d(TAG, "Failure: ${it.message}") })
        )
    }

    companion object {
        private val TAG = CurrencyListViewModel::class.java.simpleName
    }
}
