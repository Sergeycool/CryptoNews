package com.example.cryptoapp.presentation.viewmodel.news

import androidx.lifecycle.MutableLiveData
import com.example.cryptoapp.data.model.News
import com.example.cryptoapp.toolchain.mvvmbase.BaseViewModel

class ArticleViewModel : BaseViewModel() {
    val article: MutableLiveData<News> = MutableLiveData()

    private fun getNews() {
//        disposables.add(
//
//        )
    }




}
