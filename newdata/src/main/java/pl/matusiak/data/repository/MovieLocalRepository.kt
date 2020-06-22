package pl.matusiak.data.repository

interface MovieLocalRepository {
    fun saveMovieToFavourites(movieId: Int)

    fun removeMovieFromFavourites(movieId: Int)
}