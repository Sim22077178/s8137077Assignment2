package com.example.s8137077assignment2

import com.example.s8137077assignment2.data.model.DashboardResponse
import com.example.s8137077assignment2.data.model.LoginRequest
import com.example.s8137077assignment2.data.model.LoginResponse
import com.example.s8137077assignment2.network.ApiService

class FakeApiService : ApiService {

    override suspend fun login(
        request: LoginRequest
    ): LoginResponse {

        return LoginResponse(
            keypass = "testTopic"
        )
    }

    override suspend fun getDashboard(
        keypass: String
    ): DashboardResponse {

        return DashboardResponse(
            entities = listOf(
                mapOf(
                    "property1" to "Test Value 1",
                    "property2" to "Test Value 2",
                    "description" to "Test description"
                )
            ),
            entityTotal = 1
        )
    }
}