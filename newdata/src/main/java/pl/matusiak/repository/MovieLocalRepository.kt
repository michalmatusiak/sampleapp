package pl.matusiak.repository

interface MovieLocalRepository {
    fun saveMovieToFavourites(movieId: Int)

    fun removeMovieFromFavourites(movieId: Int)
}