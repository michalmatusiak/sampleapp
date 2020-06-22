package pl.matusiak.data.model

import pl.matusiak.data.model.appmodel.MovieModel
import pl.matusiak.data.model.dto.MovieDto

fun MovieDto.toModel() =
    MovieModel(
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