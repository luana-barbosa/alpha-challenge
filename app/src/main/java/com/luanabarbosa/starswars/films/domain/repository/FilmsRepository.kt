package com.luanabarbosa.starswars.films.domain.repository

import com.luanabarbosa.starswars.films.data.model.ResultFilmsModel

interface FilmsRepository {
    suspend fun getFilms(page: Int): Result<ResultFilmsModel>
}