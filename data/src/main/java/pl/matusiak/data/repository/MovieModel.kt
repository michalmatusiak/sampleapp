package pl.matusiak.data.repository

data class MovieModel(
    val forAdult: Boolean,
    val backdropImagePath: String?,
    val id: Int,
    val overview: String,
    val releaseDate: String,
    val title: String,
    val video: Boolean,
    val voteAverage: Double,
    val voteCount: Int
)