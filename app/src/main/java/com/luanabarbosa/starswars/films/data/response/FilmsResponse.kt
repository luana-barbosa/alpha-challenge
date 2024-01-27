package com.luanabarbosa.starswars.films.data.response

import com.google.gson.annotations.SerializedName

data class ResultFilmsResponse(
    @SerializedName("next")
    var next: String? = null,
    @SerializedName("prev")
    var prev: String? = null,
    @SerializedName("results")
    var results: List<FilmsResponse>,
)

data class FilmsResponse(
    @SerializedName("title")
    var title: String? = null,
    @SerializedName("opening_crawl")
    var openingCrawl: String? = null,
    @SerializedName("director")
    var director: String? = null,
    @SerializedName("producer")
    var producer: String? = null,
    @SerializedName("release_date")
    var releaseDate: String? = null
)
