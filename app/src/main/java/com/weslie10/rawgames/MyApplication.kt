package com.weslie10.rawgames

import android.app.Application
import com.weslie10.rawgames.core.di.databaseModule
import com.weslie10.rawgames.core.di.networkModule
import com.weslie10.rawgames.core.di.repositoryModule
import com.weslie10.rawgames.di.useCaseModule
import com.weslie10.rawgames.di.viewModelModule
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
                    viewModelModule
                )
            )
        }
    }
}