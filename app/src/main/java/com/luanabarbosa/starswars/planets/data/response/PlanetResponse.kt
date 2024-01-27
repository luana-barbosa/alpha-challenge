package com.luanabarbosa.starswars.planets.data.response

import com.google.gson.annotations.SerializedName

data class ResultPlanetResponse(
    @SerializedName("next")
    var next: String? = null,
    @SerializedName("prev")
    var prev: String? = null,
    @SerializedName("results")
    var results: List<PlanetResponse>,
)

data class PlanetResponse(
    @SerializedName("name")
    var name: String? = null,
    @SerializedName("rotation_period")
    var rotationPeriod: String? = null,
    @SerializedName("orbital_period")
    var orbitalPeriod: String? = null,
    @SerializedName("diameter")
    var diameter: String? = null,
    @SerializedName("climate")
    var climate: String? = null,
    @SerializedName("terrain")
    var terrain: String? = null,
    @SerializedName("population")
    var population: String? = null
)
