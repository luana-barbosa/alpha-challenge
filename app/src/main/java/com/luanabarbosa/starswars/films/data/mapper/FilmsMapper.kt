package com.luanabarbosa.starswars.films.data.mapper

import com.luanabarbosa.starswars.films.data.model.ResultFilmsModel
import com.luanabarbosa.starswars.films.data.response.ResultFilmsResponse

interface FilmsMapper {
    fun mapToFilmsModel(
        response: ResultFilmsResponse,
    ): ResultFilmsModel
}
