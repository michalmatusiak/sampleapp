package pl.matusiak.data.network

import io.reactivex.rxjava3.core.Single
import pl.matusiak.data.model.dto.MovieDtoWrapper
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieService {

    @GET("movie/now_playing")
    fun getNowPlayingMovie(
        @Query("page") page: Int = 1
    ): Single<MovieDtoWrapper>

    @GET("search/movie")
    fun searchSuggestion(
        @Query("query") searchText: String
    ): Single<MovieDtoWrapper>
}