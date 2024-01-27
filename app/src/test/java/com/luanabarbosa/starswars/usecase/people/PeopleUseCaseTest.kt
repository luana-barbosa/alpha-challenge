package com.luanabarbosa.starswars.usecase.people

import com.luanabarbosa.starswars.mocks.PeopleMocks
import com.luanabarbosa.starswars.people.domain.repository.PeopleRepository
import com.luanabarbosa.starswars.people.domain.usecase.GetPeopleCharacters
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
class PeopleUseCaseTest {

    // Mock repository
    private val repository: PeopleRepository = mockk()
    private val getPeople = GetPeopleCharacters(repository)

    @Before
    fun setUp() {
        clearAllMocks()
    }

    @Test
    fun `invoke() should return list of PeopleListModel on success`() = runBlocking {
        // Given
        coEvery { repository.getPeopleUser(2) } returns Result.success(PeopleMocks.result)

        // When
        val result = getPeople.invoke(2)

        // Then
        assertTrue(result.isSuccess)
        assertEquals(PeopleMocks.result, result.getOrNull())
    }

    @Test
    fun `invoke() should return an error on failure`() = runBlocking {
        // Given
        val error = RuntimeException("Test error")
        coEvery { repository.getPeopleUser(2) } returns Result.failure(error)

        // When
        val result = getPeople.invoke(2)

        // Then
        assertTrue(result.isFailure)
        assertEquals(error, result.exceptionOrNull())
    }
}
