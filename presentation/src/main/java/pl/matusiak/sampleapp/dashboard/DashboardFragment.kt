package pl.matusiak.sampleapp.dashboard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Filter
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.dashboard_fragment.*
import pl.matusiak.sampleapp.BR
import pl.matusiak.sampleapp.R
import pl.matusiak.sampleapp.core.di.ViewModelFactory
import pl.matusiak.sampleapp.dashboard.MoviePageListAdapter.MoviesListInteractor
import pl.matusiak.sampleapp.databinding.DashboardFragmentBinding
import pl.matusiak.sampleapp.details.MovieDetailsFragment
import pl.matusiak.sampleapp.details.MovieDetailsInteractor
import pl.matusiak.sampleapp.model.MovieUiModel
import pl.matusiak.sampleapp.utils.onTextChanged
import javax.inject.Inject


class DashboardFragment : DaggerFragment(),
    MoviesListInteractor, MovieDetailsInteractor {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private lateinit var viewDataBinding: DashboardFragmentBinding
    private lateinit var moviePageListAdapter: MoviePageListAdapter
    private lateinit var suggestionAdapter: ArrayAdapter<String>
    private lateinit var filter: Filter

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
        setupDataBindings()
        subscribeUi()
        observeData()
        viewModel.subscribe()
    }

    override fun onDestroyView() {
        viewModel.unsubscribe()
        super.onDestroyView()
    }

    override fun movieStarClick(movieModel: MovieUiModel) {
        viewModel.starClicked(movieModel)
    }

    override fun moviewItemClick(movieModel: MovieUiModel) {
        val supportFragmentManager = activity?.supportFragmentManager
        val detailsFragment = MovieDetailsFragment.newInstance(movieModel)
        supportFragmentManager?.apply {
            beginTransaction()
                .add(
                    R.id.fragmentContainer,
                    detailsFragment
                )
                .addToBackStack(null)
                .commit()
        }
        detailsFragment.setDetailsInteractor(this)
    }

    override fun starInDetailsClicked(movieUiModel: MovieUiModel) {
        moviePageListAdapter.refreshItem(movieUiModel)
    }

    private fun subscribeUi() {
        moviePageListAdapter = MoviePageListAdapter(this)
        recycler.adapter = moviePageListAdapter

        suggestionAdapter = ArrayAdapter(
            requireContext(),
            R.layout.support_simple_spinner_dropdown_item
        )
        autoCompleteTextView.apply {
            setAdapter(suggestionAdapter)
            onTextChanged {
                viewModel.searchTextChanged(it)
            }
        }
    }

    private fun setupDataBindings() {
        viewDataBinding.apply {
            setVariable(BR.viewModel, viewModel)
            lifecycleOwner = this@DashboardFragment
            executePendingBindings()
        }
    }

    private fun observeData() {
        viewModel.movieList.observe(viewLifecycleOwner, Observer {
            moviePageListAdapter.setItems(it)
        })

        viewModel.suggestionList.observe(viewLifecycleOwner, Observer { it ->
            suggestionAdapter.apply {
                clear()
                addAll(it.map { it.title })
                notifyDataSetChanged()
            }
        })
    }

}