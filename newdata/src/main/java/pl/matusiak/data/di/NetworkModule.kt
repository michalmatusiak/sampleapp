package pl.matusiak.data.di

import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import hu.akarnokd.rxjava3.retrofit.RxJava3CallAdapterFactory
import io.reactivex.rxjava3.schedulers.Schedulers
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import pl.matusiak.data.network.AuthInterceptor
import pl.matusiak.data.network.MovieService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
class NetworkModule {

    companion object {
        private const val BASE_URL = "https://api.themoviedb.org/3/"
        const val IMAGE_URL = "https://image.tmdb.org/t/p/w500/"
    }

    @Provides
    @Singleton
    fun provideKotlin2RetrofitJSON2(
        okHttpClient: OkHttpClient,
        gson: Gson
    ): Retrofit = Retrofit.Builder()
        .client(okHttpClient)
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create(gson))
        .addCallAdapterFactory(RxJava3CallAdapterFactory.createWithScheduler(Schedulers.io()))
        .build()

    @Provides
    @Singleton
    internal fun provideMovieService(retrofit: Retrofit): MovieService =
        retrofit.create(MovieService::class.java)


    @Provides
    @Singleton
    fun provideAuthInterceptor() = AuthInterceptor()

    @Provides
    @Singleton
    fun provideLoggingInterceptor(): HttpLoggingInterceptor {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        return interceptor
    }

    @Provides
    @Singleton
    internal fun provideGson() = Gson()

    @Provides
    @Singleton
    fun okHttpClient(
        authInterceptor: AuthInterceptor,
        loggingInterceptor: HttpLoggingInterceptor
    ): OkHttpClient {

        val builder = OkHttpClient.Builder()
        return builder
            .addInterceptor(loggingInterceptor)
            .addInterceptor(authInterceptor)
            .readTimeout(2, TimeUnit.MINUTES)
            .writeTimeout(2, TimeUnit.MINUTES)
            .connectTimeout(2, TimeUnit.MINUTES)
            .build()
    }

}