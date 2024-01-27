package com.luanabarbosa.starswars.mocks

import com.luanabarbosa.starswars.planets.data.model.PlanetListModel
import com.luanabarbosa.starswars.planets.data.model.ResultPlanetModel

object PlanetsMocks {
    val result = ResultPlanetModel(
        next = "",
        prev = "",
        result = listOf(
            PlanetListModel(
                next = "",
                name = "",
                rotationPeriod = "",
                orbitalPeriod = "",
                diameter = "",
                climate = "",
                gravity = "",
                terrain = "",
                surfaceWater = "",
                population = "",
                image = ""
            )
        )
    )
}