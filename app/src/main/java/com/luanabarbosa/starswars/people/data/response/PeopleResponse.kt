package com.luanabarbosa.starswars.people.data.response

import com.google.gson.annotations.SerializedName

data class ResultResponse(
    @SerializedName("next")
    var next: String? = null,
    @SerializedName("prev")
    var prev: String? = null,
    @SerializedName("results")
    var results: List<PeopleUsersResponse>,
)

data class PeopleUsersResponse(
    @SerializedName("name")
    var name: String? = null,
    @SerializedName("height")
    var height: String? = null,
    @SerializedName("mass")
    var mass: String? = null,
    @SerializedName("hair_color")
    var hairColor: String? = null,
    @SerializedName("skin_color")
    var skinColor: String? = null,
    @SerializedName("eye_color")
    var eyeColor: String? = null,
    @SerializedName("birth_year")
    var birthYear: String? = null,
    @SerializedName("gender")
    var gender: String? = null
)
