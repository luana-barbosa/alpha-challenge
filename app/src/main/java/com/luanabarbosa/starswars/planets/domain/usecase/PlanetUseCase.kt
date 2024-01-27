package com.luanabarbosa.starswars.planets.domain.usecase

import com.luanabarbosa.starswars.planets.data.model.ResultPlanetModel
import com.luanabarbosa.starswars.planets.domain.repository.PlanetRepository

interface PlanetUseCase {
    suspend operator fun invoke(page: Int): Result<ResultPlanetModel>
}

class GetPlanetCharacters(private val repository: PlanetRepository) : PlanetUseCase {
    override suspend fun invoke(page: Int): Result<ResultPlanetModel> =
        repository.getPlanet(page)
}
