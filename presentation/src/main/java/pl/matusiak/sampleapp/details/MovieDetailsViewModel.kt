package pl.matusiak.sampleapp.details

import android.view.View
import androidx.databinding.ObservableField
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import pl.matusiak.sampleapp.R
import pl.matusiak.sampleapp.model.MovieUiModel
import javax.inject.Inject

class MovieDetailsViewModel @Inject constructor() : ViewModel() {

    val titleLiveData = ObservableField<String>()
    val dateLiveData = ObservableField<String>()
    val descriptionLiveData = ObservableField<String>()
    val favouriteStarLiveData = ObservableField<Int>()
    val imageUrlLiveData = MutableLiveData<String>()

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
    }
}