package com.example.jetpacksample

import android.app.Application
import com.example.jetpacksample.di.apiModule
import com.example.jetpacksample.di.repositoryModule
import com.example.jetpacksample.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class JetpackApp : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@JetpackApp)
            modules(
                listOf(apiModule, repositoryModule, viewModelModule)
            )
        }
    }
}