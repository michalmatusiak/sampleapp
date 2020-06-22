package pl.matusiak.repository

import android.content.SharedPreferences
import androidx.core.content.edit
import io.reactivex.rxjava3.core.Single
import pl.matusiak.MovieService

class MovieRepositoryImpl(
    private val movieService: MovieService,
    private val sharedPreferences: SharedPreferences
) : MovieRemoteRepository, MovieLocalRepository {

    override fun getNowPlayingMovies(): Single<List<MovieModel>> =
        movieService
            .getNowPlayingMovie()
            .map { dtoWrapper ->
                dtoWrapper.results.map {
                    val isFavourite = sharedPreferences.getBoolean(it.id.toString(), false)
                    val appModel = it.toModel()
                    appModel.isFavourite = isFavourite
                    appModel
                }
            }


    override fun getSearchSuggestion(searchText: String): Single<List<MovieModel>> =
        movieService
            .searchSuggestion(searchText)
            .map { dtoWrapper ->
                dtoWrapper.results.map {
                    val isFavourite = sharedPreferences.getBoolean(it.id.toString(), false)
                    val appModel = it.toModel()
                    appModel.isFavourite = isFavourite
                    appModel
                }
            }

    override fun saveMovieToFavourites(movieId: Int) {
        sharedPreferences.edit {
            putBoolean(movieId.toString(), true)
        }
    }

    override fun removeMovieFromFavourites(movieId: Int) {
        sharedPreferences.edit {
            remove(movieId.toString())
        }
    }

}
