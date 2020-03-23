package com.example.cryptoapp.toolchain.mvvmbase

import androidx.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable

abstract class BaseViewModel : ViewModel() {
    val disposables = CompositeDisposable()
//    private val mOnBackClicked: SingleLiveEvent<Any> = SingleLiveEvent()
    override fun onCleared() {
        super.onCleared()
        // Using dispose will clear all and set isDisposed = true, so it will not accept any new disposable
        disposables.dispose()
    }

//    val onBackClicked: SingleLiveEvent<Any>
//        get() = mOnBackClicked
//
//    fun onBackClicked() = mOnBackClicked.call()
}
