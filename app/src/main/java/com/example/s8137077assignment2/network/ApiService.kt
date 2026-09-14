package com.example.s8137077assignment2.network

import com.example.s8137077assignment2.data.model.DashboardResponse
import com.example.s8137077assignment2.data.model.LoginRequest
import com.example.s8137077assignment2.data.model.LoginResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface ApiService {

    // Sends the student's login credentials to the Sydney authentication endpoint.
    // The API returns a keypass when authentication is successful
    @POST("sydney/auth")
    suspend fun login(
        @Body request: LoginRequest
    ): LoginResponse

    // Uses the keypass received from the login request to retrieve
    // the list of entities for the dashboard.
    @GET("dashboard/{keypass}")
    suspend fun getDashboard(
        @Path("keypass") keypass: String
    ): DashboardResponse
}