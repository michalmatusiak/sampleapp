package pl.matusiak.data.di

import android.content.Context
import android.content.SharedPreferences
import dagger.Module
import dagger.Provides
import pl.matusiak.data.network.MovieService
import pl.matusiak.data.repository.MovieLocalRepository
import pl.matusiak.data.repository.MovieRemoteRepository
import pl.matusiak.data.repository.MovieRepositoryImpl
import javax.inject.Singleton

@Module
class DataStorageModule {

    companion object {
        private const val SHARED_PREFERENCES = "shared_preferences"
    }

    @Provides
    @Singleton
    fun sharedPreferences(context: Context) =
        context.getSharedPreferences(SHARED_PREFERENCES, Context.MODE_PRIVATE)!!

    @Provides
    @Singleton
    internal fun provideMovieRemoteRepository(
        service: MovieService,
        sharedPreferences: SharedPreferences
    ): MovieRemoteRepository {
        return MovieRepositoryImpl(service, sharedPreferences)
    }

    @Provides
    @Singleton
    internal fun provideMovieLocalRepository(
        service: MovieService,
        sharedPreferences: SharedPreferences
    ): MovieLocalRepository {
        return MovieRepositoryImpl(service, sharedPreferences)
    }
}