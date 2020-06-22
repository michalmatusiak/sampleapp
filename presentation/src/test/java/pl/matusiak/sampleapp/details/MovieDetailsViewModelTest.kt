package pl.matusiak.sampleapp.details

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import io.mockk.MockKAnnotations
import io.mockk.impl.annotations.RelaxedMockK
import io.mockk.verify
import junit.framework.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import pl.matusiak.domain.FavouriteMoviesUseCase
import pl.matusiak.sampleapp.model.MovieUiModel

class MovieDetailsViewModelTest {

    private lateinit var sut: MovieDetailsViewModel

    @get:Rule
    val rule = InstantTaskExecutorRule()

    @RelaxedMockK
    lateinit var mockFavouriteMoviesUseCase: FavouriteMoviesUseCase

    @RelaxedMockK
    lateinit var mockInteractor: MovieDetailsInteractor

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        sut = MovieDetailsViewModel(mockFavouriteMoviesUseCase)
        sut.setInteractor(mockInteractor)
    }

    @Test
    fun `should show data from provided model`() {
        sut.setViewModel(getMockedMovieModel())

        assertEquals("url", sut.imageUrlLiveData.value)
        assertEquals("title", sut.titleLiveData.get())
        assertEquals("23.04", sut.dateLiveData.get())
        assertEquals("test", sut.descriptionLiveData.get())
    }

    @Test
    fun `should notify back button live data when button button has been clicked`() {
        sut.backClicked()
        assertEquals(true, sut.backButtonClickLiveData.value)
    }

    @Test
    fun `should mark movie as favourite and store it in database`() {
        val movieUiModel = getMockedMovieModel()
        movieUiModel.isFavourite = false
        sut.setViewModel(movieUiModel)

        sut.favouriteStarClicked()
        verify { mockFavouriteMoviesUseCase.addFavourite(123) }
        verify { mockInteractor.starInDetailsClicked(movieUiModel) }
    }

    private fun getMockedMovieModel() =
        MovieUiModel(
            forAdult = false,
            backdropImagePath = "url",
            id = 123,
            overview = "test",
            releaseDate = "23.04",
            title = "title",
            video = false,
            voteAverage = 3.45,
            voteCount = 123,
            isFavourite = true
        )
}