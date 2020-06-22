package pl.matusiak.sampleapp.dashboard

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.kotlin.addTo
import io.reactivex.rxjava3.kotlin.subscribeBy
import pl.matusiak.data.repository.MovieModel
import pl.matusiak.domain.NowPlayingMovieUseCase
import pl.matusiak.sampleapp.core.di.SchedulersProvider
import javax.inject.Inject

class DashboardViewModel @Inject constructor(
    private val getNowPlayingMovieUseCase: NowPlayingMovieUseCase,
    private val schedulersProvider: SchedulersProvider
) : ViewModel() {

    private val disposables = CompositeDisposable()

    val isLoadingVisibleEvent = MutableLiveData<Boolean>()
    val listEvent = MutableLiveData<List<MovieModel>>()

    fun getFilms() {
        getNowPlayingMovieUseCase
            .get()
            .subscribeOn(schedulersProvider.getBackgroundThread())
            .observeOn(schedulersProvider.getMainThread())
            .doOnSubscribe { isLoadingVisibleEvent.postValue(true) }
            .doFinally { isLoadingVisibleEvent.postValue(false) }
            .subscribeBy(
                onSuccess = {
                    listEvent.postValue(it)
                },
                onError = {

                }
            )
            .addTo(disposables)
    }

}