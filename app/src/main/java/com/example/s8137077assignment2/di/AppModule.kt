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

// Koin dependency injection module.
// This module defines and provides the dependencies used throughout the application.
val appModule = module {

    // Creates a logging interceptor to monitor HTTP requests and responses.
    // BODY level allows request and response details to be displayed during development.
    single {
        HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
    }

    // Provides a single OkHttpClient instance for handling network requests.
    // The logging interceptor is added to help with debugging API communication.
    single {
        OkHttpClient.Builder()
            .addInterceptor(get<HttpLoggingInterceptor>())
            .build()
    }

    // Creates a single Retrofit instance for communicating with the REST API.
    // GsonConverterFactory is used to convert JSON responses into Kotlin objects.
    single<Retrofit> {
        Retrofit.Builder()
            .baseUrl("https://nit3213apinew.onrender.com/")
            .client(get())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    // Creates the ApiService implementation using the Retrofit instance.
    // This provides access to the API endpoints defined in ApiService.
    single<ApiService> {
        get<Retrofit>().create(ApiService::class.java)
    }

    // Provides a single instance of AppRepository.
    // The repository receives ApiService through dependency injection.
    single {
        AppRepository(get())
    }

    // Provides LoginViewModel through Koin dependency injection.
    // The repository is automatically supplied to the ViewModel.
    viewModel {
        LoginViewModel(get())
    }

    // Provides DashboardViewModel through Koin dependency injection.
    // The repository is automatically supplied to the ViewModel.
    viewModel {
        DashboardViewModel(get())
    }
}

