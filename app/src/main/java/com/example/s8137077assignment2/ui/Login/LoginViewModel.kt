package com.example.s8137077assignment2.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.s8137077assignment2.data.repository.AppRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import retrofit2.HttpException

class LoginViewModel(

    // Represents whether the login request is currently running.
    private val repository: AppRepository
) : ViewModel() {

    // Contains the keypass returned by the API after successful login.
    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading

    // Contains an error message that can be displayed by the Login screen.
    private val _loginSuccess = MutableStateFlow<String?>(null)
    val loginSuccess: StateFlow<String?> = _loginSuccess

    private val _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> = _error

    fun login(username: String, password: String) {

        // Validate the input before making a network request.
        if (username.isBlank() || password.isBlank()) {
            _error.value = "Please enter username and password"
            return
        }

        // viewModelScope automatically cancels the coroutine when
        // the ViewModel is destroyed.
        viewModelScope.launch {

            try {

                _isLoading.value = true
                _error.value = null

                // Ask the Repository to perform authentication.
                val response = repository.login(
                    username,
                    password
                )

                // Store the keypass so the Activity can navigate
                // to the Dashboard screen.
                _loginSuccess.value = response.keypass
            } catch (e: HttpException) {

                // Convert HTTP errors into simple messages that are easier
                // for the user to understand.
                _error.value = when (e.code()) {
                    401 -> "Invalid student ID or first name."
                    404 -> "Authentication service could not be found."
                    500 -> "Ser`ver error. Please try again later."
                    else -> "Login failed. Please check your details."
                }

            } catch (e: Exception) {

                // Handles network errors and any unexpected problems.
                _error.value = "Unable to connect to the server. Please try again."

            } finally {

                // Stop the loading indicator regardless of success or failure.
                _isLoading.value = false
            }
        }
    }

    fun clearError() {
        _error.value = null
    }
}