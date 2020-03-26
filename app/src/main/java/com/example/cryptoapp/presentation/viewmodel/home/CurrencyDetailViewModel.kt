package com.example.cryptoapp.presentation.viewmodel.home

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.cryptoapp.data.model.CoinPriceInfo
import com.example.cryptoapp.domain.usecase.GetCurrencyDetailInfoUseCase
import com.example.cryptoapp.toolchain.mvvmbase.BaseViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class CurrencyDetailViewModel : BaseViewModel() {
    val detailCurrencyInfo: MutableLiveData<CoinPriceInfo> = MutableLiveData()

    fun getDetailInfo(fSym: String) {
        disposables.add(GetCurrencyDetailInfoUseCase().execute(fSym)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                    detailCurrencyInfo.value = it
            }, {
                Log.d(TAG, "Failure: ${it.message}")
            })
        )
    }

    companion object{
        private val TAG = CurrencyDetailViewModel::class.java.simpleName
    }
}
