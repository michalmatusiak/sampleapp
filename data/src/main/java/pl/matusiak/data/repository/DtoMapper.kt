package pl.matusiak.data.repository

import pl.matusiak.data.model.MovieDto

fun MovieDto.toModel() = MovieModel(
    forAdult = forAdult,
    backdropImagePath = backdropImagePath,
    id = id,
    overview = overview,
    releaseDate = releaseDate,
    title = title,
    video = video,
    voteAverage = voteAverage,
    voteCount = voteCount
)