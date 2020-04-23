package com.example.cryptoapp.domain.usecase

import com.example.cryptoapp.data.model.News
import com.example.cryptoapp.data.repository.NewsRepository
import io.reactivex.Completable

class InsertNewsUseCase {
    fun execute(newsList: List<News>): Completable =
        NewsRepository.insertNews(newsList)
}