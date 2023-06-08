package com.example.swipetask

import android.app.Application
import com.example.swipetask.di.appModule
import com.example.swipetask.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class BaseApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@BaseApplication)
            modules(listOf(appModule, viewModelModule))
        }
    }
}