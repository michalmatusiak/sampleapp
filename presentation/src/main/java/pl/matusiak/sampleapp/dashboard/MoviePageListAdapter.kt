package pl.matusiak.sampleapp.dashboard

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import pl.matusiak.sampleapp.model.MovieUiModel

class MoviePageListAdapter(private val interactor: MoviesListInteractor) :
    RecyclerView.Adapter<MovieViewHolder>() {

    private val items = ArrayList<MovieUiModel>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        MovieViewHolder.createViewHolder(parent)

    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.bindItem(items[position], interactor)
    }

    fun setItems(listOf: List<MovieUiModel>) {
        items.clear()
        items.addAll(listOf)
        notifyDataSetChanged()
    }

    fun refreshItem(movieUiModel: MovieUiModel) {
        val movieIndex = items.indexOf(movieUiModel)
        notifyItemChanged(movieIndex)
    }

    interface MoviesListInteractor {
        fun movieStarClick(movieModel: MovieUiModel)
        fun moviewItemClick(movieModel: MovieUiModel)
    }
}