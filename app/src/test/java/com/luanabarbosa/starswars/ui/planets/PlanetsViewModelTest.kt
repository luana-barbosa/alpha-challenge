package com.luanabarbosa.starswars.ui.planets

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.luanabarbosa.starswars.mocks.PlanetsMocks
import com.luanabarbosa.starswars.planets.data.model.PlanetListModel
import com.luanabarbosa.starswars.planets.domain.usecase.PlanetUseCase
import com.luanabarbosa.starswars.planets.ui.PlanetViewModel
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import io.mockk.spyk
import io.mockk.verifyOrder
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.setMain
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class PlanetsViewModelTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()
    private val testDispatcher = TestCoroutineDispatcher()

    private val planetsUseCase: PlanetUseCase = mockk()
    private lateinit var viewModel: PlanetViewModel

    @Before
    fun setUp() {
        Dispatchers.setMain(testDispatcher)
        viewModel = PlanetViewModel(planetsUseCase)
    }

    @Test
    fun `test getPlanets success`() = runBlocking {
        // Given
        val observerPlanets = spyk<Observer<List<PlanetListModel>>>()

        coEvery { planetsUseCase(any()) } returns Result.success(PlanetsMocks.result)

        viewModel.planets.observeForever(observerPlanets)

        // When
        viewModel.getPlanets()

        // Then
        coVerify { planetsUseCase(any()) }
        verifyOrder {
            observerPlanets.onChanged(PlanetsMocks.result.result)
        }
    }

    @Test
    fun `test getPlanets failure`() = runBlocking {
        // Given
        val observerError = spyk<Observer<Throwable>>()
        val mockError = RuntimeException("Test error")

        coEvery { planetsUseCase(any()) } returns Result.failure(mockError)

        viewModel.isError.observeForever(observerError)

        // When
        viewModel.getPlanets()

        // Then
        coVerify { planetsUseCase(any()) }
        verifyOrder {
            observerError.onChanged(mockError)
        }
    }
}
