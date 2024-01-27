package com.luanabarbosa.starswars.films.data.service

import com.luanabarbosa.starswars.data.config.ConfigApi.FILMS_API_URL
import com.luanabarbosa.starswars.films.data.response.ResultFilmsResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface FilmsApi {
    @GET(FILMS_API_URL)
    suspend fun getFilms(@Query("page") page: Int): ResultFilmsResponse
}
