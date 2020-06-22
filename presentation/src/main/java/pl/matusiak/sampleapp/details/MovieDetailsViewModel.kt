package pl.matusiak.sampleapp.details

import android.view.View
import androidx.databinding.ObservableField
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import pl.matusiak.domain.FavouriteMoviesUseCase
import pl.matusiak.sampleapp.R
import pl.matusiak.sampleapp.model.MovieUiModel
import javax.inject.Inject

class MovieDetailsViewModel @Inject constructor(
    private val favouriteMoviesUseCase: FavouriteMoviesUseCase
) : ViewModel() {

    private lateinit var interactor: MovieDetailsInteractor
    val titleLiveData = ObservableField<String>()
    val dateLiveData = ObservableField<String>()
    val descriptionLiveData = ObservableField<String>()
    val favouriteStarLiveData = ObservableField<Int>()
    val imageUrlLiveData = MutableLiveData<String>()
    val backButtonClickLiveData = MutableLiveData<Boolean>()

    private lateinit var movie: MovieUiModel

    fun setViewModel(movieUiModel: MovieUiModel) {
        this.movie = movieUiModel
        imageUrlLiveData.postValue(movie.backdropImagePath)
        titleLiveData.set(movieUiModel.title)
        dateLiveData.set(movieUiModel.releaseDate)
        descriptionLiveData.set(movieUiModel.overview)
        setFavouriteStarStatus(movieUiModel)
    }

    fun favouriteStarClicked(view: View? = null) {
        movie.isFavourite = !movie.isFavourite
        setFavouriteStarStatus(movie)
        if (movie.isFavourite) {
            favouriteMoviesUseCase.addFavourite(movie.id)
        } else {
            favouriteMoviesUseCase.removeFavourite(movie.id)
        }
        interactor.starInDetailsClicked(movie)
    }

    private fun setFavouriteStarStatus(movieUiModel: MovieUiModel) {
        val starRes = if (movieUiModel.isFavourite) R.drawable.ic_star else R.drawable.ic_empty_star
        favouriteStarLiveData.set(starRes)
    }

    fun backClicked(view: View? = null) {
        backButtonClickLiveData.postValue(true)
    }

    fun setInteractor(interactor: MovieDetailsInteractor) {
        this.interactor = interactor
    }
}