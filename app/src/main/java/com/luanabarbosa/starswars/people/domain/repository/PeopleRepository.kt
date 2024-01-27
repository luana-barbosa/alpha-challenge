package com.luanabarbosa.starswars.people.domain.repository

import com.luanabarbosa.starswars.people.data.model.ResultModel

interface PeopleRepository {
    suspend fun getPeopleUser(page: Int): Result<ResultModel>
}