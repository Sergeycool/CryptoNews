package com.example.cryptoapp.presentation.viewmodel.home

import android.util.Log
import com.example.cryptoapp.data.database.AppDatabase
import com.example.cryptoapp.domain.usecase.GetTopCoinsInfoUseCase
import com.example.cryptoapp.presentation.App
import com.example.cryptoapp.toolchain.mvvmbase.BaseViewModel
import io.reactivex.schedulers.Schedulers

class CurrencyViewModel : BaseViewModel() {

    private val db = AppDatabase.getInstance(App.context)

    val priceList = db.coinPriceInfoDao().getPriceList() //TODO migrate to mutable live data

//    fun getDetailInfo(fSym: String): LiveData<CoinPriceInfo> {
//        return db.coinPriceInfoDao().getPriceInfoAboutCoin(fSym)
//    }

    init {
        loadData()
    }

    private fun loadData() {
        disposables.add(
            GetTopCoinsInfoUseCase().execute(limit = 10)
//                .delaySubscription(5, TimeUnit.SECONDS)
//                .repeat()
//                .retry()
                .subscribeOn(Schedulers.io())
                .subscribe({
                    if (it.isCompleteWithoutError()) {
                        it.result?.let { priceList ->
                            db.coinPriceInfoDao().insertPriceList(priceList) }
                    }
                }, {
                    Log.d(TAG, "Failure: ${it.message}")
                })
        )
    }

    companion object {
        private val TAG = CurrencyViewModel::class.java.simpleName
    }
}
