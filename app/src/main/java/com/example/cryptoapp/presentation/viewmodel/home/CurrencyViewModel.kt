package com.example.cryptoapp.presentation.viewmodel.home

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.cryptoapp.data.database.AppDatabase
import com.example.cryptoapp.data.model.CoinPriceInfo
import com.example.cryptoapp.domain.usecase.GetLastPriceListUseCase
import com.example.cryptoapp.domain.usecase.GetTopCoinsInfoUseCase
import com.example.cryptoapp.presentation.App
import com.example.cryptoapp.toolchain.mvvmbase.BaseViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import java.util.concurrent.TimeUnit

class CurrencyViewModel : BaseViewModel() {

    private val db = AppDatabase.getInstance(App.context)
    val coinDataList: MutableLiveData<List<CoinPriceInfo>> = MutableLiveData()

    init {
        loadData()
    }

    private fun loadData() {
        disposables.add(
            GetTopCoinsInfoUseCase().execute(limit = 10)
                .delaySubscription(5, TimeUnit.SECONDS)
                .repeat()
                .retry()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    if (it.isCompleteWithoutError()) {
                        it.result?.let { priceList ->
                            db.coinPriceInfoDao().insertPriceList(priceList)
                        } //TODO implement Comletable
                    }
                }, {
                    Log.d(TAG, "Failure: ${it.message}")
                })
        )
    }

    fun getLastPriceList() {
        disposables.add(
            GetLastPriceListUseCase().execute()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ coinDataList.value = it },
                    { Log.d(TAG, "Failure: ${it.message}") })
        )
    }

    companion object {
        private val TAG = CurrencyViewModel::class.java.simpleName
    }
}
