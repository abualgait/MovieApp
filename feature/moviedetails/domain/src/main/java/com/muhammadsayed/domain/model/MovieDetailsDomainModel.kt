package com.muhammadsayed.domain.model

data class MovieDetailsDomainModel(
    val id: Int,
    val title: String,
    val backDropPath: String,
    val overview: String,
    val releaseDate: String,
    val genres: List<MovieDetailsGenreDomainModel>,
) {
    companion object {
        const val DEFAULT_TITLE = ""
        const val DEFAULT_BACK_DROP_PATH = ""
        const val DEFAULT_OVERVIEW = ""
        const val DEFAULT_RELEASE_DATE = ""
        val DEFAULT_GENRES = emptyList<MovieDetailsGenreDomainModel>()
    }
}

data class MovieDetailsGenreDomainModel(
    val id: Int,
    val name: String
) {
    companion object {
        const val DEFAULT_NAME = ""
    }
}

