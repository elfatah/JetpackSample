package com.example.jetpacksample.repository


import com.example.jetpacksample.model.NewsResponse
import com.example.jetpacksample.model.SourceResponse
import retrofit2.http.GET
import retrofit2.http.Query


interface NewsService {
    @GET("articles")
    suspend fun getNews(
        @Query("source") source: String,
        @Query("apiKey") apiKey: String,
        @Query("sortBy") sortBy: String
    ): NewsResponse

    @GET("sources")
    suspend fun getSources(
        @Query("category") category: String,
        @Query("language") language: String,
        @Query("country") country: String
    ): SourceResponse

}