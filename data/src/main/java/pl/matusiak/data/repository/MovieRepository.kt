package pl.matusiak.data.repository

import io.reactivex.rxjava3.core.Single

interface MovieRepository {

    fun getNowPlayingMovies(): Single<List<MovieModel>>
}
