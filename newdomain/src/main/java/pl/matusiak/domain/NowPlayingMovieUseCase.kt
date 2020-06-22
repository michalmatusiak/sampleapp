package pl.matusiak.domain

import pl.matusiak.repository.MovieRemoteRepository

class NowPlayingMovieUseCase(private val repository: MovieRemoteRepository) {

    fun get() = repository.getNowPlayingMovies()
}