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

    private lateinit var model: MovieUiModel

    fun setViewModel(movieUiModel: MovieUiModel) {
        this.model = movieUiModel
        imageUrlLiveData.postValue(model.backdropImagePath)
        titleLiveData.set(movieUiModel.title)
        dateLiveData.set(movieUiModel.releaseDate)
        descriptionLiveData.set(movieUiModel.overview)
        val starRes = if (movieUiModel.isFavourite) R.drawable.ic_star else R.drawable.ic_empty_star
        favouriteStarLiveData.set(starRes)
    }

    fun favouriteStarClicked(view: View) {
        model.isFavourite = !model.isFavourite
        val starRes = if (model.isFavourite) R.drawable.ic_star else R.drawable.ic_empty_star
        favouriteStarLiveData.set(starRes)
        if (model.isFavourite) {
            favouriteMoviesUseCase.addFavourite(model.id)
        } else {
            favouriteMoviesUseCase.removeFavourite(model.id)
        }
        interactor.starInDetailsClicked(model)
    }

    fun backClicked(view: View) {
        backButtonClickLiveData.postValue(true)
    }

    fun setInteractor(interactor: MovieDetailsInteractor) {
        this.interactor = interactor
    }
}