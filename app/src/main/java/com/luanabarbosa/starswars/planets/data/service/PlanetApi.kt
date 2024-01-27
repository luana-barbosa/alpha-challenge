package com.luanabarbosa.starswars.planets.data.service

import com.luanabarbosa.starswars.data.config.ConfigApi.PLANETS_API_URL
import com.luanabarbosa.starswars.planets.data.response.ResultPlanetResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface PlanetApi {
    @GET(PLANETS_API_URL)
    suspend fun getPlanet(@Query("page") page: Int): ResultPlanetResponse
}
