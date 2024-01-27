package com.luanabarbosa.starswars.usecase.planets

import com.luanabarbosa.starswars.mocks.PlanetsMocks
import com.luanabarbosa.starswars.planets.domain.repository.PlanetRepository
import com.luanabarbosa.starswars.planets.domain.usecase.GetPlanetCharacters
import io.mockk.clearAllMocks
import io.mockk.coEvery
import io.mockk.mockk
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertTrue
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

@ExperimentalCoroutinesApi
class PlanetsUseCaseTest {

    // Mock repository
    private val repository: PlanetRepository = mockk()
    private val getPlanets = GetPlanetCharacters(repository)

    @Before
    fun setUp() {
        clearAllMocks()
    }

    @Test
    fun `invoke() should return list of PlanetListModel on success`() = runBlocking {
        // Given
        coEvery { repository.getPlanet(2) } returns Result.success(PlanetsMocks.result)

        // When
        val result = getPlanets.invoke(2)

        // Then
        assertTrue(result.isSuccess)
        assertEquals(PlanetsMocks.result, result.getOrNull())
    }

    @Test
    fun `invoke() should return an error on failure`() = runBlocking {
        // Given
        val error = RuntimeException("Test error")
        coEvery { repository.getPlanet(2) } returns Result.failure(error)

        // When
        val result = getPlanets.invoke(2)

        // Then
        assertTrue(result.isFailure)
        assertEquals(error, result.exceptionOrNull())
    }
}
