package com.example.s8137077assignment2.dashboard

import com.example.s8137077assignment2.FakeApiService
import com.example.s8137077assignment2.MainDispatcherRule
import com.example.s8137077assignment2.data.repository.AppRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertNotNull
import org.junit.Rule
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class DashboardViewModelTest {

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    @Test
    fun loadDashboard_returnsEntities() = runTest {

        val fakeApi = FakeApiService()
        val repository = AppRepository(fakeApi)
        val viewModel = DashboardViewModel(repository)

        viewModel.loadDashboard("testTopic")

        advanceUntilIdle()

        val dashboard = viewModel.dashboard.value

        assertNotNull(dashboard)

        assertEquals(
            1,
            dashboard?.entityTotal
        )

        assertEquals(
            "Test Value 1",
            dashboard?.entities?.first()?.get("property1")
        )
    }

    @Test
    fun loadDashboard_stopsLoading() = runTest {

        val fakeApi = FakeApiService()
        val repository = AppRepository(fakeApi)
        val viewModel = DashboardViewModel(repository)

        viewModel.loadDashboard("testTopic")

        advanceUntilIdle()

        assertFalse(viewModel.isLoading.value)
    }
}