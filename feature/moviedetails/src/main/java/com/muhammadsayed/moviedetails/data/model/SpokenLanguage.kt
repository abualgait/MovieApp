package com.muhammadsayed.moviedetails.data.model

import com.squareup.moshi.Json


data class SpokenLanguage(
    @Json(name = "english_name")
    val englishName: String,
    @Json(name = "iso_639_1")
    val iso: String,
    val name: String
)