package com.example.cryptoapp.presentation.viewmodel.home

import androidx.lifecycle.MutableLiveData
import com.example.cryptoapp.toolchain.mvvmbase.BaseViewModel
import com.example.cryptoapp.toolchain.mvvmbase.SingleLiveEvent

class HomeSharedViewModel : BaseViewModel() {
    val currentFragment: MutableLiveData<Int> = MutableLiveData()

    val eventNavigateToDetailCoin: SingleLiveEvent<String> = SingleLiveEvent()

    fun navigateToDetailCoin(fromSymbol: String) {
        eventNavigateToDetailCoin.value = fromSymbol
    }

    fun setCurrentFragment(index: Int) {
        currentFragment.value = index
    }


}