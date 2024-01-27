package com.luanabarbosa.starswars.planets.data.mapper

import com.luanabarbosa.starswars.planets.data.model.PlanetListModel
import com.luanabarbosa.starswars.planets.data.model.ResultPlanetModel
import com.luanabarbosa.starswars.planets.data.response.ResultPlanetResponse

class PlanetMapperImpl : PlanetMapper {

    override fun mapToPlanetModel(response: ResultPlanetResponse) = ResultPlanetModel(
        next = response.next.orEmpty(),
        result = response.results.map {
            PlanetListModel(
                name = it.name.orEmpty(),
                rotationPeriod = it.rotationPeriod.orEmpty(),
                orbitalPeriod = it.orbitalPeriod.orEmpty(),
                diameter = it.diameter.orEmpty(),
                climate = it.climate.orEmpty(),
                terrain = it.terrain.orEmpty(),
                population = it.population.orEmpty()
            )
        }
    )
}
