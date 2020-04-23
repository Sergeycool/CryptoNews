package com.example.cryptoapp.presentation.viewmodel.news

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.cryptoapp.data.model.News
import com.example.cryptoapp.domain.usecase.GetAllNewsUseCase
import com.example.cryptoapp.domain.usecase.GetCashedNewsUseCase
import com.example.cryptoapp.domain.usecase.InsertNewsUseCase
import com.example.cryptoapp.toolchain.mvvmbase.BaseViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class NewsListViewModel : BaseViewModel() {

    init {
        getCachedNews()
        updateNews()
    }

    val newsList: MutableLiveData<List<News>> = MutableLiveData()


    private fun getCachedNews() {
        disposables.add(GetCashedNewsUseCase().execute()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe{ newsList.value = it })
    }

    private fun updateNews() {
        disposables.add(
            GetAllNewsUseCase().execute()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    if (it.result != null) {
                        newsList.value = it.result.data
                        it.result.data?.let { newsResult -> saveUpdatedData(newsResult) }
                    }

                }, { Log.d(TAG, "Failure: ${it.message}") })
        )
    }

    private fun saveUpdatedData(newsList: List<News>) {
        InsertNewsUseCase().execute(newsList)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe()
    }

    companion object {
        private val TAG = NewsListViewModel::class.java.simpleName
    }

}