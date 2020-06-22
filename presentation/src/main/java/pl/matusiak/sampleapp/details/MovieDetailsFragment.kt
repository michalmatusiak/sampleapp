package pl.matusiak.sampleapp.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.movie_details_fragment.*
import pl.matusiak.sampleapp.BR
import pl.matusiak.sampleapp.R
import pl.matusiak.sampleapp.core.di.ViewModelFactory
import pl.matusiak.sampleapp.databinding.MovieDetailsFragmentBinding
import pl.matusiak.sampleapp.model.MovieUiModel
import javax.inject.Inject

class MovieDetailsFragment() : DaggerFragment() {

    companion object {
        private const val KEY_MOVIE_MODEL = "movie_model"
        fun newInstance(movieModel: MovieUiModel): MovieDetailsFragment {
            val movieDetailsFragment = MovieDetailsFragment()
            val bundle = Bundle()
            bundle.putParcelable(KEY_MOVIE_MODEL, movieModel)
            movieDetailsFragment.arguments = bundle
            return movieDetailsFragment
        }
    }

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private lateinit var viewDataBinding: MovieDetailsFragmentBinding
    private lateinit var interactor: MovieDetailsInteractor

    private val viewModel by lazy {
        ViewModelProvider(this, viewModelFactory)
            .get(MovieDetailsViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewDataBinding =
            DataBindingUtil.inflate(inflater, R.layout.movie_details_fragment, container, false)
        return viewDataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupDataBindings()
        observeData()
        checkArguments()
    }

    fun setDetailsInteractor(interactor: MovieDetailsInteractor) {
        this.interactor = interactor
    }

    private fun checkArguments() {
        val movieUiModel = arguments?.getParcelable<MovieUiModel>(KEY_MOVIE_MODEL)
        movieUiModel?.let {
            viewModel.setViewModel(it)
            viewModel.setInteractor(interactor)
        }
    }

    private fun observeData() {
        viewModel.imageUrlLiveData.observe(viewLifecycleOwner, Observer {
            Glide
                .with(requireContext())
                .load(it)
                .centerCrop()
                .placeholder(R.drawable.ic_empty_star)
                .into(movieAvatarImage)
        })

        viewModel.backButtonClickLiveData.observe(viewLifecycleOwner, Observer {
            activity?.onBackPressed()
        })
    }

    private fun setupDataBindings() {
        viewDataBinding.setVariable(BR.viewModel, viewModel)
        viewDataBinding.lifecycleOwner = this
        viewDataBinding.executePendingBindings()
    }

}