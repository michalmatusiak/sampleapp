package pl.matusiak.sampleapp.dashboard

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.row_moview_item.view.*
import pl.matusiak.sampleapp.R
import pl.matusiak.sampleapp.model.MovieUiModel

class MovieViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    companion object {
        fun createViewHolder(parent: ViewGroup): MovieViewHolder {
            val layout = LayoutInflater.from(parent.context)
                .inflate(R.layout.row_moview_item, parent, false)
            return MovieViewHolder(layout)
        }
    }

    fun bindItem(
        movieModel: MovieUiModel,
        interactor: MoviePageListAdapter.MoviesListInteractor
    ) {
        itemView.setOnClickListener { interactor.moviewItemClick(movieModel) }
        itemView.movieTitleText.text = movieModel.title
        itemView.movieDateText.text = movieModel.releaseDate
        itemView.movieDescriptionText.text = movieModel.overview
        setStarState(movieModel)
        itemView.movieFavouriteStarImage.setOnClickListener {
            movieModel.isFavourite = !movieModel.isFavourite
            setStarState(movieModel)
            interactor.movieStarClick(movieModel)
        }

        Glide
            .with(itemView)
            .load(movieModel.backdropImagePath)
            .centerCrop()
            .placeholder(R.drawable.ic_empty_star)
            .into(itemView.movieAvatarImage)
    }

    private fun setStarState(movieModel: MovieUiModel) {
        val imageRes = if (movieModel.isFavourite) {
            R.drawable.ic_star
        } else {
            R.drawable.ic_empty_star
        }
        itemView.movieFavouriteStarImage.setImageResource(imageRes)
    }
}