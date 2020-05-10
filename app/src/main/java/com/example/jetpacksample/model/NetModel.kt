package com.example.jetpacksample.model

import com.google.gson.annotations.SerializedName

data class NewsResponse(
    @SerializedName("sortBy")
    val sortBy: String? = null,

    @SerializedName("source")
    val source: String? = null,

    @SerializedName("articles")
    var articles: List<NewsItemResponse>? = null,

    @SerializedName("status")
    val status: String? = null
)

data class NewsItemResponse(
    @SerializedName("publishedAt")
    var publishedAt: String? = null,

    @SerializedName("author")
    var author: String? = null,

    @SerializedName("urlToImage")
    var urlToImage: String? = null,

    @SerializedName("description")
    var description: String? = null,

    @SerializedName("title")
    var title: String? = null,

    @SerializedName("url")
    var url: String? = null
)

data class SourceResponse (

    @SerializedName("sources")
    var sources: List<SourcesItemResponse>? = null,

    @SerializedName("status")
    val status: String? = null
)

data class SourcesItemResponse (
    @SerializedName("country")
    var country: String? = null,

    @SerializedName("urlsToLogos")
    var urlsToLogos: UrlToLogosResponse? = null,

    @SerializedName("name")
    var name: String? = null,

    @SerializedName("sortBysAvailable")
    var sortBysAvailable: List<String?>? = null,

    @SerializedName("description")
    var description: String? = null,

    @SerializedName("language")
    var language: String? = null,

    @SerializedName("id")
    var id: String? = null,

    @SerializedName("category")
    var category: String? = null,

    @SerializedName("url")
    var url: String? = null
)

data class UrlToLogosResponse (

    @SerializedName("small")
    var small: String? = null,

    @SerializedName("large")
    var large: String? = null,

    @SerializedName("medium")
    var medium: String? = null
)