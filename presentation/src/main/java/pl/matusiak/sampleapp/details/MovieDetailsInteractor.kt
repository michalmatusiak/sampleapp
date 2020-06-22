package pl.matusiak.sampleapp.details

import pl.matusiak.sampleapp.model.MovieUiModel

interface MovieDetailsInteractor {

    fun starInDetailsClicked(movieUiModel: MovieUiModel)
}