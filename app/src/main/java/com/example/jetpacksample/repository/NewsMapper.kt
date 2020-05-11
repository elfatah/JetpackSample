package com.example.jetpacksample.repository

import com.example.jetpacksample.model.News
import com.example.jetpacksample.model.NewsItem
import com.example.jetpacksample.model.NewsItemResponse
import com.example.jetpacksample.model.NewsResponse

class NewsMapper {
    fun fromNewsResponse(newsResponse: NewsResponse): News {
        return News(
            sortBy = newsResponse.sortBy ?: "",
            articles = newsResponse.articles?.map(::fromNewsItemResponse) ?: listOf(),
            source = newsResponse.source ?: "",
            status = newsResponse.status ?: ""
        )
    }

    private fun fromNewsItemResponse(newsItemResponse: NewsItemResponse): NewsItem {
        return NewsItem(
            publishedAt = newsItemResponse.publishedAt ?: "",
            author = newsItemResponse.author ?: "",
            description = newsItemResponse.description ?: "",
            title = newsItemResponse.title ?: "",
            url = newsItemResponse.url ?: "",
            urlToImage = newsItemResponse.urlToImage ?: ""
        )
    }
}