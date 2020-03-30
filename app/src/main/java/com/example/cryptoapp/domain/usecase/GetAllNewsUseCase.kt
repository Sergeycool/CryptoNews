package com.example.cryptoapp.domain.usecase

import com.example.cryptoapp.data.model.GetNewsResponse
import com.example.cryptoapp.data.repository.NewsRepository
import com.example.cryptoapp.domain.DomainConstants.PARAM_LANGUAGE
import com.example.cryptoapp.toolchain.rxjava.RxMessage
import com.example.cryptoapp.toolchain.rxjava.RxResponseException
import io.reactivex.Observable
import io.reactivex.Single

class GetAllNewsUseCase {
    fun execute(lang: String = PARAM_LANGUAGE): Observable<RxMessage<GetNewsResponse>> {

        val startSingle: Single<RxMessage<GetNewsResponse>> =
            Single.just(RxMessage.onStart())

        val requestSingle: Single<RxMessage<GetNewsResponse>> = NewsRepository
            .getAllNews(lang)
            .map {
                if (it.isSuccessful)
                    RxMessage.onNext(it.body())
                else
                    throw RxResponseException(it.code(), it.message())
            }
            .onErrorReturn { RxMessage.onError(it) }


        return Observable.concat(startSingle.toObservable(), requestSingle.toObservable())
    }
}