package pl.matusiak.di

import dagger.Module
import dagger.Provides
import pl.matusiak.data.repository.MovieRepository
import pl.matusiak.domain.NowPlayingMovieUseCase

@Module
class DomainModule {

    @Provides
    fun getNowPlayingUseCase(repository: MovieRepository) = NowPlayingMovieUseCase(repository)
}