package com.luanabarbosa.starswars.ui.films

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.luanabarbosa.starswars.films.data.model.FilmsListModel
import com.luanabarbosa.starswars.films.domain.usecase.FilmsUseCase
import com.luanabarbosa.starswars.films.ui.FilmsViewModel
import com.luanabarbosa.starswars.mocks.FilmsMocks
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import io.mockk.spyk
import io.mockk.verify
import io.mockk.verifyOrder
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class FilmsViewModelTest {

    @get:Rule
    val rule = InstantTaskExecutorRule()

    private val filmsUseCase: FilmsUseCase = mockk()
    private val filmsViewModel = FilmsViewModel(filmsUseCase)

    private val testCoroutineDispatcher = TestCoroutineDispatcher()

    @Before
    fun setup() {
        Dispatchers.setMain(testCoroutineDispatcher)
    }

    @After
    fun cleanup() {
        Dispatchers.resetMain()
        testCoroutineDispatcher.cleanupTestCoroutines()
    }

    @Test
    fun `test getFilms success`() = runBlocking {
        // Given
        val observerFilms = spyk<Observer<List<FilmsListModel>>>()

        coEvery { filmsUseCase(any()) } returns Result.success(FilmsMocks.result)

        filmsViewModel.films.observeForever(observerFilms)

        // When
        filmsViewModel.getFilms()

        // Then
        coVerify { filmsUseCase(any()) }
        verifyOrder {
            observerFilms.onChanged(FilmsMocks.result.result)
        }
    }

    @Test
    fun `test getFilms failure`() {
        // Arrange
        coEvery { filmsUseCase.invoke(any()) } returns Result.failure(RuntimeException("Some error"))

        // Mock Observer
        val observer = spyk<Observer<Throwable>>()
        filmsViewModel.isError.observeForever(observer)

        // Act
        filmsViewModel.getFilms()

        // Assert
        assert(filmsViewModel.films.value == null)

        // Advance the coroutine dispatcher to execute the coroutine
        testCoroutineDispatcher.advanceUntilIdle()

        assert(filmsViewModel.films.value == null)
        verify { observer.onChanged(any()) }
    }
}
