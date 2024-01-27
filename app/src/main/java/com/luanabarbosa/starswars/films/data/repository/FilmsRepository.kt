package com.luanabarbosa.starswars.films.data.repository

import com.luanabarbosa.starswars.films.data.mapper.FilmsMapper
import com.luanabarbosa.starswars.films.data.model.ResultFilmsModel
import com.luanabarbosa.starswars.films.data.service.FilmsApi
import com.luanabarbosa.starswars.films.domain.repository.FilmsRepository
import retrofit2.HttpException

class FilmsRepositoryImpl(
    private val service: FilmsApi,
    private val mapper: FilmsMapper
) : FilmsRepository {

    override suspend fun getFilms(page: Int): Result<ResultFilmsModel> {
        return try {
            val request = service.getFilms(page)
            Result.success(mapper.mapToFilmsModel(request))
        } catch (e: HttpException) {
            Result.failure(exception = e)
        }
    }
}
