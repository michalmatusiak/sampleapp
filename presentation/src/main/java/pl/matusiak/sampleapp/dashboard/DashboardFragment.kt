package pl.matusiak.sampleapp.dashboard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.dashboard_fragment.*
import pl.matusiak.data.repository.MovieModel
import pl.matusiak.sampleapp.BR
import pl.matusiak.sampleapp.R
import pl.matusiak.sampleapp.core.di.ViewModelFactory
import pl.matusiak.sampleapp.databinding.DashboardFragmentBinding
import javax.inject.Inject

class DashboardFragment : DaggerFragment(),
    MoviePageListAdapter.MoviesListInteractor {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private lateinit var viewDataBinding: DashboardFragmentBinding

    private val viewModel by lazy {
        ViewModelProvider(this, viewModelFactory)
            .get(DashboardViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewDataBinding =
            DataBindingUtil.inflate(inflater, R.layout.dashboard_fragment, container, false)
        return viewDataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewDataBinding.setVariable(BR.viewModel, viewModel)
        viewDataBinding.lifecycleOwner = this
        viewDataBinding.executePendingBindings()
        val moviePageListAdapter = MoviePageListAdapter(this)
        recycler.adapter = moviePageListAdapter
        viewModel.listEvent.observe(viewLifecycleOwner, Observer {
            moviePageListAdapter.setItems(it)
        })
        viewModel.getFilms()
    }

    override fun movieStarClick(movieModel: MovieModel) {

    }

    override fun moviewItemClick(movieModel: MovieModel) {

    }

}