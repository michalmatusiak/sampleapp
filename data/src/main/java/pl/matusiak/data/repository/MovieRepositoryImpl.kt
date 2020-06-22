package pl.matusiak.data.repository

import io.reactivex.rxjava3.core.Single
import pl.matusiak.data.MovieService

class MovieRepositoryImpl(private val movieService: MovieService) : MovieRepository {

    override fun getNowPlayingMovies(): Single<List<MovieModel>> =
        movieService
            .getNowPlayingMovie()
            .map { dtoWrapper -> dtoWrapper.results.map { it.toModel() } }
}
