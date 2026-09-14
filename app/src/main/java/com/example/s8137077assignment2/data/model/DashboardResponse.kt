package com.example.s8137077assignment2.data.model

data class DashboardResponse(
    val entities: List<Map<String, Any?>>,
    val entityTotal: Int
)