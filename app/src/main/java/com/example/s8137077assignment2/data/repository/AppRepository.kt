package com.example.s8137077assignment2.data.repository

import com.example.s8137077assignment2.data.model.DashboardResponse
import com.example.s8137077assignment2.data.model.LoginRequest
import com.example.s8137077assignment2.data.model.LoginResponse
import com.example.s8137077assignment2.network.ApiService

class AppRepository(
    private val apiService: ApiService
) {
    // Handles authentication by sending the username and password
    // to the API through Retrofit.
    suspend fun login(
        username: String,
        password: String
    ): LoginResponse {

        return apiService.login(
            LoginRequest(
                username = username,
                password = password
            )
        )
    }

    // Retrieves dashboard data using the keypass returned after login.
    suspend fun getDashboard(
        keypass: String
    ): DashboardResponse {

        return apiService.getDashboard(keypass)
    }
}