package pl.matusiak.sampleapp.dashboard

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import pl.matusiak.data.repository.MovieModel

class MoviePageListAdapter(private val interactor: MoviesListInteractor) :
    RecyclerView.Adapter<MovieViewHolder>() {

    private val items = ArrayList<MovieModel>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        MovieViewHolder.createViewHolder(parent)

    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.bindItem(items[position], interactor)
    }

    fun setItems(listOf: List<MovieModel>) {
        items.addAll(listOf)
        notifyDataSetChanged()
    }

    interface MoviesListInteractor {
        fun movieStarClick(movieModel: MovieModel)
        fun moviewItemClick(movieModel: MovieModel)
    }
}