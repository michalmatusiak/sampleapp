package pl.matusiak.sampleapp.details

import android.view.View
import androidx.databinding.ObservableField
import androidx.lifecycle.ViewModel
import pl.matusiak.sampleapp.R
import pl.matusiak.sampleapp.model.MovieUiModel
import javax.inject.Inject

class MovieDetailsViewModel @Inject constructor() : ViewModel() {

    val titleLiveData = ObservableField<String>()
    val dateLiveData = ObservableField<String>()
    val descriptionLiveData = ObservableField<String>()
    val favouriteStarLiveData = ObservableField<Int>()

    fun setViewModel(movieUiModel: MovieUiModel) {
        titleLiveData.set(movieUiModel.title)
        dateLiveData.set(movieUiModel.releaseDate)
        descriptionLiveData.set(movieUiModel.overview)
        val starRes = if (movieUiModel.isFavourite) R.drawable.ic_star else R.drawable.ic_empty_star
        favouriteStarLiveData.set(starRes)
    }

    fun favouriteStarClicked(view: View) {

    }
}