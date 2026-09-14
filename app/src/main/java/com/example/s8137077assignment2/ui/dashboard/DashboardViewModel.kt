package com.example.s8137077assignment2.dashboard

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.s8137077assignment2.data.model.DashboardResponse
import com.example.s8137077assignment2.data.repository.AppRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class DashboardViewModel(
    private val repository: AppRepository
) : ViewModel() {

    // Holds the dashboard response received from the API.
    private val _dashboard = MutableStateFlow<DashboardResponse?>(null)
    val dashboard: StateFlow<DashboardResponse?> = _dashboard

    // Controls the loading indicator while the API request is running.
    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading

    // Stores an error message if the dashboard request fails.
    private val _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> = _error

    fun loadDashboard(keypass: String) {

        // Perform the network request without blocking the UI thread.
        viewModelScope.launch {

            try {

                _isLoading.value = true
                _error.value = null

                // Retrieve dashboard entities using the keypass
                // received during authentication.
                _dashboard.value =
                    repository.getDashboard(keypass)

            } catch (e: Exception) {

                // Display an error if the API request fails.
                _error.value =
                    e.message ?: "Unable to load dashboard."

            } finally {

                _isLoading.value = false
            }
        }
    }
}