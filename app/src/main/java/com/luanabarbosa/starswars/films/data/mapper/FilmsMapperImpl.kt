package com.luanabarbosa.starswars.films.data.mapper

import com.luanabarbosa.starswars.films.data.model.FilmsListModel
import com.luanabarbosa.starswars.films.data.model.ResultFilmsModel
import com.luanabarbosa.starswars.films.data.response.ResultFilmsResponse

class FilmsMapperImpl : FilmsMapper {

    override fun mapToFilmsModel(response: ResultFilmsResponse) = ResultFilmsModel(
        next = response.next.orEmpty(),
        result = response.results.map {
            FilmsListModel(
                title = it.title.orEmpty(),
                openingCrawl = it.openingCrawl.orEmpty(),
                director = it.director.orEmpty(),
                producer = it.producer.orEmpty(),
                releaseDate = it.releaseDate.orEmpty()
            )
        }
    )
}
