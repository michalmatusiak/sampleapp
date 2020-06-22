package pl.matusiak.model.dto

import com.google.gson.annotations.SerializedName

data class MovieDtoWrapper(val results: List<MovieDto>)

data class MovieDto(
    @SerializedName("adult")
    val forAdult: Boolean,
    @SerializedName("backdrop_path")
    val backdropImagePath: String?,
    val id: Int,
    val overview: String,
    @SerializedName("release_date")
    val releaseDate: String,
    val title: String,
    val video: Boolean,
    @SerializedName("vote_average")
    val voteAverage: Double,
    @SerializedName("vote_count")
    val voteCount: Int
)