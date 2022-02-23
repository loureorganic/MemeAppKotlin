package com.example.bookappkotlin.app

import android.app.Application
import com.example.bookappkotlin.di.*
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidFileProperties
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

//DSL Application where Koin has been initialized
class App : Application() {

    override fun onCreate() {

        super.onCreate()
        initKoin()
    }

    private fun initKoin() {
        //create a container configuration and register it in the to allow the use of GlobalContext
        startKoin {
            androidLogger(Level.ERROR)
            androidContext(this@App)
            androidFileProperties()
            // set a list of Koin modules to load in the container
            modules(
                listOf(
                    repositoryModule,
                    apiModule,
                    registerRepositoryModule,
                    loginRepositoryModule,
                    splashRepositoryModule,
                    glideModule,
                    photoAdapterModule,
                )
            )
        }
    }
}