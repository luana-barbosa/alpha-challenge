package com.luanabarbosa.starswars.films.domain.usecase

import com.luanabarbosa.starswars.films.data.model.ResultFilmsModel
import com.luanabarbosa.starswars.films.domain.repository.FilmsRepository

interface FilmsUseCase {
    suspend operator fun invoke(page: Int): Result<ResultFilmsModel>
}

class GetFilmsCharacters(private val repository: FilmsRepository) : FilmsUseCase {
    override suspend fun invoke(page: Int): Result<ResultFilmsModel> =
        repository.getFilms(page)
}
