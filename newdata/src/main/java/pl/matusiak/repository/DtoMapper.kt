package pl.matusiak.repository

import pl.matusiak.model.dto.MovieDto

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