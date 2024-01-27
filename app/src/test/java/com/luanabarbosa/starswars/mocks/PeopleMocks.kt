package com.luanabarbosa.starswars.mocks

import com.luanabarbosa.starswars.people.data.model.PeopleListModel
import com.luanabarbosa.starswars.people.data.model.ResultModel
import com.luanabarbosa.starswars.planets.data.model.PlanetListModel
import com.luanabarbosa.starswars.planets.data.model.ResultPlanetModel

object PeopleMocks {
    val result = ResultModel(
        next = "",
        prev = "",
        result = listOf(
            PeopleListModel(
                next = "",
                name = "",
                height = "",
                mass = "",
                hairColor = "",
                skinColor = "",
                eyeColor = "",
                birthYear = "",
                gender = "",
                image = ""
            )
        )
    )
}