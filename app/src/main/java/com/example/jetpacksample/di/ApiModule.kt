package com.example.jetpacksample.di

import android.content.Context
import com.example.jetpacksample.repository.NewsService
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.readystatesoftware.chuck.ChuckInterceptor
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val apiModule = module {
    single { provideOkHttpClient(interceptors = get(), context = androidContext()) }
    single { provideGson() }
    single { provideApiService(retrofit = get()) }
    single { provideConverterFactory(gson = get()) }
    single { provideRetrofit(okHttpClient = get(), factory = get()) }
    single { provideHttpInterceptors(context = androidContext()) }
}

fun provideRetrofit(okHttpClient: OkHttpClient, factory: Converter.Factory): Retrofit =
    Retrofit.Builder()
        .baseUrl("https://newsapi.org/v1/")
        .client(okHttpClient)
        .addConverterFactory(factory)
        .build()

fun provideApiService(retrofit: Retrofit): NewsService =
    retrofit.create(NewsService::class.java)

fun provideOkHttpClient(interceptors: List<Interceptor>, context: Context): OkHttpClient {
    return OkHttpClient.Builder()
        .apply {
            interceptors.map(::addInterceptor)
        }
        .build()
}

fun provideHttpInterceptors(context: Context): List<Interceptor> =
    listOf(
        HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BODY },
        ChuckInterceptor(context)
    )

fun provideConverterFactory(gson: Gson): Converter.Factory {
    return GsonConverterFactory.create(gson)
}

fun provideGson(): Gson {
    return GsonBuilder().disableHtmlEscaping().create()
}
