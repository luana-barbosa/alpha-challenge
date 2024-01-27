package com.luanabarbosa.starswars.people.data.service

import com.luanabarbosa.starswars.data.config.ConfigApi.USERS_API_URL
import com.luanabarbosa.starswars.people.data.response.ResultResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface PeopleApi {
    @GET(USERS_API_URL)
    suspend fun getPeople(@Query("page") page: Int): ResultResponse
}
