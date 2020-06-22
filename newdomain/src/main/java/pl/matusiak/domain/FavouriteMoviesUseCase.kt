package pl.matusiak.domain

import pl.matusiak.repository.MovieLocalRepository

class FavouriteMoviesUseCase(private val movieLocalRepository: MovieLocalRepository) {

    fun addFavourite(movieId: Int) {
        movieLocalRepository.saveMovieToFavourites(movieId)
    }

    fun removeFavourite(movieId: Int) {
        movieLocalRepository.removeMovieFromFavourites(movieId)
    }
}