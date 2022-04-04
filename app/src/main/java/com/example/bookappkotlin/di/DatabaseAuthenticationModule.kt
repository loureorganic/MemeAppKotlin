package com.example.bookappkotlin.di

import com.example.bookappkotlin.repositories.helpper.AuthenticationHelper
import com.example.bookappkotlin.repositories.helpper.DatabaseAuthenticationHelper
import org.koin.dsl.module

val databaseAuthenticationModule = module {
    single<AuthenticationHelper> {
        DatabaseAuthenticationHelper()
    }
}