package com.example.cryptoapp.domain.usecase

import com.example.cryptoapp.data.repository.NewsRepository

class GetCashedNewsUseCase {
    fun execute() = NewsRepository.getCachedNews()
}