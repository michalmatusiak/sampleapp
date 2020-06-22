package pl.matusiak.sampleapp.dashboard

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.kotlin.addTo
import io.reactivex.rxjava3.kotlin.subscribeBy
import io.reactivex.rxjava3.subjects.PublishSubject
import pl.matusiak.domain.NowPlayingMovieUseCase
import pl.matusiak.domain.SearchSuggestionUseCase
import pl.matusiak.sampleapp.core.di.SchedulersProvider
import pl.matusiak.sampleapp.model.MovieUiModel
import pl.matusiak.sampleapp.model.toUiModel
import timber.log.Timber
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class DashboardViewModel @Inject constructor(
    private val getNowPlayingMovieUseCase: NowPlayingMovieUseCase,
    private val searchSuggestionUseCase: SearchSuggestionUseCase,
    private val schedulersProvider: SchedulersProvider
) : ViewModel() {

    private val disposables = CompositeDisposable()
    private val searchSubject = PublishSubject.create<String>()

    val isLoadingVisibleEvent = MutableLiveData<Boolean>()
    val movieList = MutableLiveData<List<MovieUiModel>>()
    val suggestionList = MutableLiveData<List<MovieUiModel>>()

    fun subscribe() {
        subscribeSearchSubject()
        getFilms()
    }

    fun unsubscribe() {
        disposables.clear()
    }

    fun searchTextChanged(searchText: CharSequence?) {
        if (!searchText.isNullOrBlank() && !searchText.isNullOrEmpty()) {
            searchSubject.onNext(searchText.toString())
        }
    }

    private fun subscribeSearchSubject() {
        searchSubject
            .debounce(200, TimeUnit.MILLISECONDS)
            .flatMap {
                searchSuggestionUseCase.search(it)
                    .toObservable()
                    .map { items ->
                        items.map { movieModel ->
                            movieModel.toUiModel()
                        }
                    }

            }
            .subscribeOn(schedulersProvider.getBackgroundThread())
            .observeOn(schedulersProvider.getMainThread())
            .subscribeBy(
                onNext = {
                    suggestionList.postValue(it)
                },
                onError = {
                    Timber.e(it)
                }
            )
            .addTo(disposables)
    }

    private fun getFilms() {
        getNowPlayingMovieUseCase
            .get()
            .map { items ->
                items.map { movieModel ->
                    movieModel.toUiModel()
                }
            }
            .subscribeOn(schedulersProvider.getBackgroundThread())
            .observeOn(schedulersProvider.getMainThread())
            .doOnSubscribe { isLoadingVisibleEvent.postValue(true) }
            .doFinally { isLoadingVisibleEvent.postValue(false) }
            .subscribeBy(
                onSuccess = {
                    movieList.postValue(it)
                },
                onError = {

                }
            )
            .addTo(disposables)
    }

}