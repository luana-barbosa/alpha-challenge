package com.luanabarbosa.starswars.films.data.model

import com.luanabarbosa.starswars.people.data.model.PeopleListModel
import java.io.Serializable

data class ResultFilmsModel(
    var next: String = "",
    var prev: String = "",
    var result: List<FilmsListModel>
) : Serializable

data class FilmsListModel(
    var next: String = "",
    var title: String = "",
    var openingCrawl: String = "",
    var director: String = "",
    var producer: String = "",
    var releaseDate: String = "",
    var image: String = ""
) : Serializable {
    companion object {
        val EMPTY = PeopleListModel()
    }
}
