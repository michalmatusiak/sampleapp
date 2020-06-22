package pl.matusiak.domain.di

import dagger.Module
import dagger.Provides
import pl.matusiak.domain.FavouriteMoviesUseCase
import pl.matusiak.domain.NowPlayingMovieUseCase
import pl.matusiak.domain.SearchSuggestionUseCase
import pl.matusiak.repository.MovieLocalRepository
import pl.matusiak.repository.MovieRemoteRepository
import javax.inject.Singleton

@Module
class DomainModule {

    @Singleton
    @Provides
    fun getNowPlayingUseCase(repository: MovieRemoteRepository) =
        NowPlayingMovieUseCase(repository)

    @Singleton
    @Provides
    fun getSearchSuggestionUseCase(repository: MovieRemoteRepository) =
        SearchSuggestionUseCase(repository)

    @Singleton
    @Provides
    fun favouriteMoviesUseCase(repository: MovieLocalRepository) =
        FavouriteMoviesUseCase(repository)
}