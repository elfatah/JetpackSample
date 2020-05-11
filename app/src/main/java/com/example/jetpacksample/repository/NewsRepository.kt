package com.example.jetpacksample.repository

import com.example.jetpacksample.model.News

interface NewsRepository {
    suspend fun getNews(source: String): News
}

class NewsRepositoryImpl(private val newsService: NewsService, private val newsMapper: NewsMapper) :
    NewsRepository {
    override suspend fun getNews(source: String): News {
        return newsService.getNews(source, "a637297ff901442493f6e38209f78c25", "")
            .let(newsMapper::fromNewsResponse)
    }
}