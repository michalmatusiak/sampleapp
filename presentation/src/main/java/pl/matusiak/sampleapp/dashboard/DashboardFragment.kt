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
import pl.matusiak.sampleapp.databinding.DashboardFragmentBinding
import pl.matusiak.sampleapp.details.MovieDetailsFragment
import pl.matusiak.sampleapp.model.MovieUiModel
import pl.matusiak.sampleapp.utils.onTextChanged
import javax.inject.Inject


class DashboardFragment : DaggerFragment(),
    MoviePageListAdapter.MoviesListInteractor {

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

        viewModel.movieList.observe(viewLifecycleOwner, Observer {
            moviePageListAdapter.setItems(it)
        })

        viewModel.suggestionList.observe(viewLifecycleOwner, Observer { it ->
            autoCompleteTextView.post {
                suggestionAdapter.clear()
                val map = it.map { it.title }
                suggestionAdapter.addAll(map)
                suggestionAdapter.notifyDataSetChanged()
            }

            autoCompleteTextView.handler.post {
                autoCompleteTextView.showDropDown();
            }

        })

        viewModel.subscribe()
    }

    private fun subscribeUi() {
        moviePageListAdapter = MoviePageListAdapter(this)
        recycler.adapter = moviePageListAdapter

        filter = object : Filter() {
            override fun performFiltering(p0: CharSequence?): FilterResults {
                TODO("Not yet implemented")
            }

            override fun publishResults(p0: CharSequence?, p1: FilterResults?) {
                TODO("Not yet implemented")
            }

        }

        suggestionAdapter = ArrayAdapter(
            requireContext(),
            R.layout.support_simple_spinner_dropdown_item
        )
        autoCompleteTextView.threshold = 1
        autoCompleteTextView.setAdapter(suggestionAdapter)
        autoCompleteTextView.onTextChanged {
            viewModel.searchTextChanged(it)
        }
    }

    private fun setupDataBindings() {
        viewDataBinding.setVariable(BR.viewModel, viewModel)
        viewDataBinding.lifecycleOwner = this
        viewDataBinding.executePendingBindings()
    }

    override fun movieStarClick(movieModel: MovieUiModel) {

    }

    override fun moviewItemClick(movieModel: MovieUiModel) {
        val supportFragmentManager = activity?.supportFragmentManager
        supportFragmentManager?.apply {
            beginTransaction()
                .add(
                    R.id.fragmentContainer,
                    MovieDetailsFragment.newInstance(movieModel)
                )
                .addToBackStack(null)
                .commit()
        }
    }

}