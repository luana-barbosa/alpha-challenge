package com.luanabarbosa.starswars.mocks

import com.luanabarbosa.starswars.films.data.model.FilmsListModel
import com.luanabarbosa.starswars.films.data.model.ResultFilmsModel

object FilmsMocks {
    val result = ResultFilmsModel(
        next = "",
        prev = "",
        result = listOf(
            FilmsListModel(
                next = "",
                title = "",
                openingCrawl = "",
                director = "",
                producer = "",
                releaseDate = "",
                image = ""
            )
        )
    )
}