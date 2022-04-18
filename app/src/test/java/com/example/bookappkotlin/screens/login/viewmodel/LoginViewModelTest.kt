package com.example.bookappkotlin.screens.login.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.bookappkotlin.screens.login.model.UserLogin
import com.example.bookappkotlin.screens.login.services.LoginService
import com.example.bookappkotlin.screens.login.services.UserLoginServices
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import org.koin.dsl.module
import org.koin.test.KoinTest
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class LoginViewModelTest : KoinTest {


    @get:Rule
    val rule = InstantTaskExecutorRule()

    @Before
    fun before() {
        startKoin {
            modules(module {
             single<ViewModelLogin>{
                    LoginViewModel()
             }

                single<LoginService>{
                    UserLoginServices()
                }
            })
        }
    }

    @After
    fun after(){
        stopKoin()
    }

    @Test
    fun testViewModel(){

        val user = UserLogin(
            email = "kaiqueguimaraes@gmail.com",
            password = "123456"
        )

        val viewModel1 = ViewModelLoginTest()

        viewModel1.loginUser(user)
        viewModel1.booleanLoginAccountLiveData.observeForever{ it ->
            assert(it === true)
        }
    }
}