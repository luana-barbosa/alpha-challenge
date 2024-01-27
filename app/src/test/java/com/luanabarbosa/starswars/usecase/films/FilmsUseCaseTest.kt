package com.luanabarbosa.starswars.usecase.films

import com.luanabarbosa.starswars.films.domain.repository.FilmsRepository
import com.luanabarbosa.starswars.films.domain.usecase.GetFilmsCharacters
import com.luanabarbosa.starswars.mocks.FilmsMocks
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
class FilmsUseCaseTest {

    // Mock repository
    private val repository: FilmsRepository = mockk()
    private val getFilms = GetFilmsCharacters(repository)

    @Before
    fun setUp() {
        clearAllMocks()
    }

    @Test
    fun `invoke() should return list of FilmsListModel on success`() = runBlocking {
        // Given
        coEvery { repository.getFilms(2) } returns Result.success(FilmsMocks.result)

        // When
        val result = getFilms.invoke(2)

        // Then
        assertTrue(result.isSuccess)
        assertEquals(FilmsMocks.result, result.getOrNull())
    }

    @Test
    fun `invoke() should return an error on failure`() = runBlocking {
        // Given
        val error = RuntimeException("Test error")
        coEvery { repository.getFilms(2) } returns Result.failure(error)

        // When
        val result = getFilms.invoke(2)

        // Then
        assertTrue(result.isFailure)
        assertEquals(error, result.exceptionOrNull())
    }
}
