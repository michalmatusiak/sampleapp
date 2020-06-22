package pl.matusiak.di

import dagger.Module
import dagger.Provides
import pl.matusiak.data.repository.MovieRepository
import pl.matusiak.domain.NowPlayingMovieUseCase
import pl.matusiak.domain.SearchSuggestionUseCase
import javax.inject.Singleton

@Module
class DomainModule {

    @Singleton
    @Provides
    fun getNowPlayingUseCase(repository: MovieRepository) =
        NowPlayingMovieUseCase(repository)

    @Singleton
    @Provides
    fun getSearchSuggestionUseCase(repository: MovieRepository) =
        SearchSuggestionUseCase(repository)
}