package com.example.cryptoapp.presentation.viewmodel.home

import com.example.cryptoapp.toolchain.mvvmbase.BaseViewModel
import com.example.cryptoapp.toolchain.mvvmbase.SingleLiveEvent

class HomeSharedViewModel : BaseViewModel() {

    val eventNavigateToDetailCoin: SingleLiveEvent<Unit> = SingleLiveEvent()




    fun navigateToDetailCoin() = eventNavigateToDetailCoin.call()


}