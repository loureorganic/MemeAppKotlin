package com.example.bookappkotlin.di

import com.example.bookappkotlin.splash.repository.RepositorySplash
import com.example.bookappkotlin.splash.repository.SplashRepository
import com.example.bookappkotlin.splash.services.SplashService
import com.example.bookappkotlin.splash.services.SplashServices
import org.koin.dsl.module


val splashRepositoryModule = module {

    single<RepositorySplash>{
        SplashRepository(preferences = get())
    }

    factory<SplashService>{
        SplashServices(get())
    }
}