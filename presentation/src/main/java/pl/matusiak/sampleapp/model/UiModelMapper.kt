package pl.matusiak.sampleapp.model

import pl.matusiak.data.di.NetworkModule.Companion.IMAGE_URL
import pl.matusiak.data.model.appmodel.MovieModel

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