package com.luanabarbosa.starswars.planets.domain.repository

import com.luanabarbosa.starswars.planets.data.model.ResultPlanetModel

interface PlanetRepository {
    suspend fun getPlanet(page: Int): Result<ResultPlanetModel>
}