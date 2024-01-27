package com.luanabarbosa.starswars.planets.data.repository

import com.luanabarbosa.starswars.planets.domain.repository.PlanetRepository
import com.luanabarbosa.starswars.planets.data.mapper.PlanetMapper
import com.luanabarbosa.starswars.planets.data.model.ResultPlanetModel
import com.luanabarbosa.starswars.planets.data.service.PlanetApi
import retrofit2.HttpException

class PlanetRepositoryImpl(
    private val service: PlanetApi,
    private val mapper: PlanetMapper
) : PlanetRepository {

    override suspend fun getPlanet(page: Int): Result<ResultPlanetModel> {
        return try {
            val request = service.getPlanet(page)
            Result.success(mapper.mapToPlanetModel(request))
        } catch (e: HttpException) {
            Result.failure(exception = e)
        }
    }
}
