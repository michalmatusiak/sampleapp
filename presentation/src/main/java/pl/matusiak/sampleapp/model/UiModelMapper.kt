package pl.matusiak.sampleapp.model

import pl.matusiak.data.repository.MovieModel

fun MovieModel.toUiModel() = MovieUiModel(
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