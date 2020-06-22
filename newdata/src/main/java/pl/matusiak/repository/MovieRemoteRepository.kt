package pl.matusiak.repository

import io.reactivex.rxjava3.core.Single

interface MovieRemoteRepository {

    fun getNowPlayingMovies(): Single<List<MovieModel>>

    fun getSearchSuggestion(searchText: String): Single<List<MovieModel>>

}
