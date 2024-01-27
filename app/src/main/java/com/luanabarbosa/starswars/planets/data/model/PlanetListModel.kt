package com.luanabarbosa.starswars.planets.data.model

import com.luanabarbosa.starswars.people.data.model.PeopleListModel
import java.io.Serializable

data class ResultPlanetModel(
    var next: String = "",
    var prev: String = "",
    var result: List<PlanetListModel>
) : Serializable

data class PlanetListModel(
    var next: String = "",
    var name: String = "",
    var rotationPeriod: String = "",
    var orbitalPeriod: String = "",
    var diameter: String = "",
    var climate: String = "",
    var terrain: String = "",
    var population: String = "",
    var image: String = ""
) : Serializable {
    companion object {
        val EMPTY = PeopleListModel()
    }
}
