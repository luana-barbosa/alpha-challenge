package com.luanabarbosa.starswars.people.data.mapper

import com.luanabarbosa.starswars.people.data.model.PeopleListModel
import com.luanabarbosa.starswars.people.data.model.ResultModel
import com.luanabarbosa.starswars.people.data.response.ResultResponse

class PeopleMapperImpl : PeopleMapper {

    override fun mapToPeopleModel(response: ResultResponse) = ResultModel(
        next = response.next.orEmpty(),
        result = response.results.map {
            PeopleListModel(
                name = it.name.orEmpty(),
                height = it.height.orEmpty(),
                mass = it.mass.orEmpty(),
                hairColor = it.hairColor.orEmpty(),
                skinColor = it.skinColor.orEmpty(),
                eyeColor = it.eyeColor.orEmpty(),
                birthYear = it.birthYear.orEmpty(),
                gender = it.gender.orEmpty()
            )
        }
    )
}
