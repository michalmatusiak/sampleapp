package pl.matusiak

import io.reactivex.rxjava3.core.Single
import pl.matusiak.model.dto.MovieDtoWrapper
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieService {

    @GET("movie/now_playing")
    fun getNowPlayingMovie(
        @Query("page") page: Int = 1,
        @Query("api_key") key: String = "7530c8937040eb1ebebf8dfc68091acb"
    ): Single<MovieDtoWrapper>

    @GET("search/movie")
    fun searchSuggestion(
        @Query("query") searchText: String,
        @Query("api_key") key: String = "7530c8937040eb1ebebf8dfc68091acb"
    ): Single<MovieDtoWrapper>
}