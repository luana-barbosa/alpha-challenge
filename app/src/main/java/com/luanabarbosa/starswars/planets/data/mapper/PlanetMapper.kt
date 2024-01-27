package com.luanabarbosa.starswars.planets.data.mapper

import com.luanabarbosa.starswars.planets.data.model.ResultPlanetModel
import com.luanabarbosa.starswars.planets.data.response.ResultPlanetResponse

interface PlanetMapper {
    fun mapToPlanetModel(
        response: ResultPlanetResponse,
    ): ResultPlanetModel
}
