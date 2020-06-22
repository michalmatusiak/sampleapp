package pl.matusiak.data.repository

import io.reactivex.rxjava3.core.Single
import pl.matusiak.data.model.appmodel.MovieModel

interface MovieRemoteRepository {

    fun getNowPlayingMovies(): Single<List<MovieModel>>

    fun getSearchSuggestion(searchText: String): Single<List<MovieModel>>

}
