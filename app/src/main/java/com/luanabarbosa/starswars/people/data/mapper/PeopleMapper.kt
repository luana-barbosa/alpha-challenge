package com.luanabarbosa.starswars.people.data.mapper

import com.luanabarbosa.starswars.people.data.model.ResultModel
import com.luanabarbosa.starswars.people.data.response.ResultResponse

interface PeopleMapper {
    fun mapToPeopleModel(
        response: ResultResponse,
    ): ResultModel
}
