package com.example.cryptoapp.presentation.viewmodel.rate

import android.app.Application
import android.util.Log
import androidx.lifecycle.LiveData
import com.example.cryptoapp.data.database.AppDatabase
import com.example.cryptoapp.data.model.CoinPriceInfo
import com.example.cryptoapp.domain.usecase.GetTopCoinsInfoUseCase
import com.example.cryptoapp.toolchain.mvvmbase.BaseViewModel
import io.reactivex.schedulers.Schedulers
import java.util.concurrent.TimeUnit

class CoinViewModel(application: Application) : BaseViewModel() {

    private val db = AppDatabase.getInstance(application)

    val priceList = db.coinPriceInfoDao().getPriceList()

    fun getDetailInfo(fSym: String): LiveData<CoinPriceInfo> {
        return db.coinPriceInfoDao().getPriceInfoAboutCoin(fSym)
    }

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
                .subscribe({
                    if (it.isCompleteWithoutError()) {
                        it.result?.let { it1 -> db.coinPriceInfoDao().insertPriceList(it1) }
                    }
                }, {
                    Log.d("TEST_OF_LOADING_DATA", "Failure: ${it.message}")
                })
        )
    }
}
