package com.luanabarbosa.starswars.ui.people

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.luanabarbosa.starswars.mocks.PeopleMocks
import com.luanabarbosa.starswars.people.data.model.PeopleListModel
import com.luanabarbosa.starswars.people.domain.usecase.PeopleUseCase
import com.luanabarbosa.starswars.people.ui.PeopleViewModel
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
class PeopleViewModelTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()
    private val testDispatcher = TestCoroutineDispatcher()

    private val peopleUseCase: PeopleUseCase = mockk()
    private lateinit var viewModel: PeopleViewModel

    @Before
    fun setUp() {
        Dispatchers.setMain(testDispatcher)
        viewModel = PeopleViewModel(peopleUseCase)
    }

    @Test
    fun `test getPeople success`() = runBlocking {
        // Given
        val observerPeople = spyk<Observer<List<PeopleListModel>>>()

        coEvery { peopleUseCase(any()) } returns Result.success(PeopleMocks.result)

        viewModel.people.observeForever(observerPeople)

        // When
        viewModel.getPeople()

        // Then
        coVerify { peopleUseCase(any()) }
        verifyOrder {
            observerPeople.onChanged(PeopleMocks.result.result)
        }
    }

    @Test
    fun `test getPeople failure`() = runBlocking {
        // Given
        val observerError = spyk<Observer<Throwable>>()
        val mockError = RuntimeException("Test error")

        coEvery { peopleUseCase(any()) } returns Result.failure(mockError)

        viewModel.isError.observeForever(observerError)

        // When
        viewModel.getPeople()

        // Then
        coVerify { peopleUseCase(any()) }
        verifyOrder {
            observerError.onChanged(mockError)
        }
    }
}
