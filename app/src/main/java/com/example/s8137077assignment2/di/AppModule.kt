package com.example.s8137077assignment2.di

import com.example.s8137077assignment2.data.repository.AppRepository
import com.example.s8137077assignment2.network.ApiService
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import com.example.s8137077assignment2.login.LoginViewModel
import org.koin.core.module.dsl.viewModel
import com.example.s8137077assignment2.dashboard.DashboardViewModel

val appModule = module {

    single {
        HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
    }

    single {
        OkHttpClient.Builder()
            .addInterceptor(get<HttpLoggingInterceptor>())
            .build()
    }

    single<Retrofit> {
        Retrofit.Builder()
            .baseUrl("https://nit3213apinew.onrender.com/")
            .client(get())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    single<ApiService> {
        get<Retrofit>().create(ApiService::class.java)
    }

    single {
        AppRepository(get())
    }

    viewModel {
        LoginViewModel(get())
    }

    viewModel {
        DashboardViewModel(get())
    }
}