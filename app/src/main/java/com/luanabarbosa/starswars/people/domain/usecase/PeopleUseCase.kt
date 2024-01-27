package com.luanabarbosa.starswars.people.domain.usecase

import com.luanabarbosa.starswars.people.data.model.ResultModel
import com.luanabarbosa.starswars.people.domain.repository.PeopleRepository

interface PeopleUseCase {
    suspend operator fun invoke(page: Int): Result<ResultModel>
}

class GetPeopleCharacters(private val repository : PeopleRepository) : PeopleUseCase {
    override suspend fun invoke(page: Int): Result<ResultModel> =
        repository.getPeopleUser(page)
}
