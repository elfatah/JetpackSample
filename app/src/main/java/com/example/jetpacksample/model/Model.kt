package com.example.jetpacksample.model


data class News(
    val sortBy: String,
    val source: String,
    val articles: List<NewsItem>,
    val status: String
)

data class NewsItem(
    val publishedAt: String,
    val author: String,
    val urlToImage: String,
    val description: String,
    val title: String,
    val url: String
)

data class Source(
    val sources: List<SourcesItem>,
    val status: String
)

data class SourcesItem(
    var country: String,
    var urlsToLogos: UrlToLogos,
    var name: String,
    var sortBysAvailable: List<String>,
    var description: String,
    var language: String,
    var id: String,
    var category: String,
    var url: String
)

data class UrlToLogos(
    var small: String,
    var large: String,
    var medium: String
)