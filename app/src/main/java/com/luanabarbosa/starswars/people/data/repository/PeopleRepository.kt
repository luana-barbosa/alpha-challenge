package com.luanabarbosa.starswars.people.data.repository

import com.luanabarbosa.starswars.people.data.mapper.PeopleMapper
import com.luanabarbosa.starswars.people.data.model.ResultModel
import com.luanabarbosa.starswars.people.data.service.PeopleApi
import com.luanabarbosa.starswars.people.domain.repository.PeopleRepository
import retrofit2.HttpException

class PeopleRepositoryImpl(
    private val service: PeopleApi,
    private val mapper: PeopleMapper
) : PeopleRepository {

    override suspend fun getPeopleUser(page: Int): Result<ResultModel> {
        return try {
            val request = service.getPeople(page)
            Result.success(mapper.mapToPeopleModel(request))
        } catch (e: HttpException) {
            Result.failure(exception = e)
        }
    }
}
