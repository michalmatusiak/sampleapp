package pl.matusiak.sampleapp.model

import pl.matusiak.newdata.di.NetworkModule.Companion.IMAGE_URL
import pl.matusiak.repository.MovieModel

fun MovieModel.toUiModel() = MovieUiModel(
    forAdult = forAdult,
    backdropImagePath = "$IMAGE_URL$backdropImagePath",
    id = id,
    overview = overview,
    releaseDate = releaseDate,
    title = title,
    video = video,
    voteAverage = voteAverage,
    voteCount = voteCount,
    isFavourite = isFavourite
)