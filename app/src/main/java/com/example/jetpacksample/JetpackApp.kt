package com.example.jetpacksample

import android.app.Application
import com.example.jetpacksample.di.apiModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class JetpackApp : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@JetpackApp)
            modules(
                listOf(apiModule)
            )
        }
    }
}