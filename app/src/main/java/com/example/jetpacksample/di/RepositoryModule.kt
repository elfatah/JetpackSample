package com.example.jetpacksample.di

import com.example.jetpacksample.repository.NewsMapper
import com.example.jetpacksample.repository.NewsRepository
import com.example.jetpacksample.repository.NewsRepositoryImpl
import org.koin.dsl.module

val repositoryModule = module {
    factory<NewsRepository> { NewsRepositoryImpl(get(), NewsMapper()) }
}