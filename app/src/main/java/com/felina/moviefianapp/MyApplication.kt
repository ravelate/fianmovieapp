package com.felina.moviefianapp

import android.app.Application
import com.felina.moviefianapp.core.di.databaseModule
import com.felina.moviefianapp.core.di.networkModule
import com.felina.moviefianapp.core.di.repositoryModule
import com.felina.moviefianapp.di.useCaseModule
import com.felina.moviefianapp.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger(Level.NONE)
            androidContext(this@MyApplication)
            modules(
                listOf(
                    databaseModule,
                    networkModule,
                    repositoryModule,
                    useCaseModule,
                    viewModelModule,
                )
            )
        }
    }
}