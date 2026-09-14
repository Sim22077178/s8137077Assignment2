package com.example.s8137077assignment2.login

import com.example.s8137077assignment2.FakeApiService
import com.example.s8137077assignment2.MainDispatcherRule
import com.example.s8137077assignment2.data.repository.AppRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertNull
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class LoginViewModelTest {

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    private lateinit var viewModel: LoginViewModel

    @Before
    fun setup() {

        // Use a fake API so the unit tests do not depend
        // on the real internet or the assignment server.
        val fakeApi = FakeApiService()

        val repository = AppRepository(fakeApi)

        viewModel = LoginViewModel(repository)
    }

    @Test
    fun login_withEmptyCredentials_showsError() {

        viewModel.login("", "")

        assertEquals(
            "Please enter username and password",
            viewModel.error.value
        )
    }

    @Test
    fun login_withValidCredentials_returnsKeypass() = runTest {

        viewModel.login(
            "s12345678",
            "John"
        )

        advanceUntilIdle()

        assertEquals(
            "testTopic",
            viewModel.loginSuccess.value
        )

        assertNull(viewModel.error.value)
    }

    @Test
    fun login_successfulRequest_stopsLoading() = runTest {

        viewModel.login(
            "s12345678",
            "John"
        )

        advanceUntilIdle()

        assertFalse(viewModel.isLoading.value)
    }

    @Test
    fun clearError_removesErrorMessage() {

        viewModel.login("", "")

        assertTrue(viewModel.error.value != null)

        viewModel.clearError()

        assertNull(viewModel.error.value)
    }
}