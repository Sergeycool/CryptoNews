package com.example.cryptoapp.presentation.viewmodel.home

import com.example.cryptoapp.data.model.News
import com.example.cryptoapp.toolchain.mvvmbase.BaseViewModel
import com.example.cryptoapp.toolchain.mvvmbase.SingleLiveEvent

class HomeSharedViewModel : BaseViewModel() {

    val eventNavigateToDetailCoin: SingleLiveEvent<String> = SingleLiveEvent()
    val eventNavigateToArticle: SingleLiveEvent<News> = SingleLiveEvent()

    fun navigateToDetailCoin(fromSymbol: String) {
        eventNavigateToDetailCoin.value = fromSymbol
    }

    fun navigateToArticle(news: News) {
        eventNavigateToArticle.value = news
    }

}
