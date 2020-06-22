package pl.matusiak.domain

import pl.matusiak.data.repository.MovieRepository

class NowPlayingMovieUseCase(private val repository: MovieRepository) {

    fun get() = repository.getNowPlayingMovies()
}