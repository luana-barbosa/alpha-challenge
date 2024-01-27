package com.luanabarbosa.starswars.people.data.model

import java.io.Serializable

data class ResultModel(
    var next: String = "",
    var prev: String = "",
    var result: List<PeopleListModel>
) : Serializable

data class PeopleListModel(
    var next: String = "",
    var name: String = "",
    var height: String = "",
    var mass: String = "",
    var hairColor: String = "",
    var skinColor: String = "",
    var eyeColor: String = "",
    var birthYear: String = "",
    var gender: String = "",
    var image: String = ""
) : Serializable {
    companion object {
        val EMPTY = PeopleListModel()
    }
}
