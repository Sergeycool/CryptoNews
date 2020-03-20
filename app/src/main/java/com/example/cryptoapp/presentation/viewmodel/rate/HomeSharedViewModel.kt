package com.example.cryptoapp.presentation.viewmodel.rate

import com.example.cryptoapp.toolchain.mvvmbase.BaseViewModel
import com.example.cryptoapp.toolchain.mvvmbase.SingleLiveEvent

open class HomeSharedViewModel public constructor(): BaseViewModel() {
    val eventNavigateToDetailCoin: SingleLiveEvent<Any> = SingleLiveEvent()


    fun navigateToDetailCoin() = eventNavigateToDetailCoin.call()


}