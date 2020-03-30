package com.example.cryptoapp.presentation.viewmodel.news

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.cryptoapp.data.model.News
import com.example.cryptoapp.domain.usecase.GetAllNewsUseCase
import com.example.cryptoapp.toolchain.mvvmbase.BaseViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class NewsListViewModel: BaseViewModel() {

    init {
        getNews()
    }

    val newsList: MutableLiveData<List<News>> = MutableLiveData()

    private fun getNews() {
        disposables.add(
            GetAllNewsUseCase().execute()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    if (it.isCompleteWithoutError()){
                        newsList.value = it.result?.data
                    }

                }, { Log.d(TAG, "Failure: ${it.message}") })
        )
    }

    companion object {
        private val TAG = NewsListViewModel::class.java.simpleName
    }

}