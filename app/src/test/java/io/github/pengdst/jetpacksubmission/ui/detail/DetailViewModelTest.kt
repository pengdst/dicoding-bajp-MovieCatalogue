package io.github.pengdst.jetpacksubmission.ui.detail

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import io.github.pengdst.jetpacksubmission.data.vo.Resource
import io.github.pengdst.jetpacksubmission.domain.models.Movie
import io.github.pengdst.jetpacksubmission.domain.models.TvShow
import io.github.pengdst.jetpacksubmission.domain.usecase.GetDetailMovieUsecase
import io.github.pengdst.jetpacksubmission.domain.usecase.GetDetailTvUsecase
import io.github.pengdst.jetpacksubmission.utils.DataStore
import io.github.pengdst.jetpacksubmission.utils.LiveDataTestUtil
import io.github.pengdst.jetpacksubmission.utils.MainCoroutineRule
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner

/**
 * Created on 5/17/21 by Pengkuh Dwi Septiandi (@pengdst)
 *
 * - Github https://github.com/pengdst
 * - Gitlab https://gitlab.com/pengdst
 * - LinkedIn https://linkedin.com/in/pengdst
 */

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class DetailViewModelTest {

    @get:Rule var instantTaskExecutorRule = InstantTaskExecutorRule()
    @get:Rule var mainCoroutineRule = MainCoroutineRule()

    @Mock private lateinit var getDetailMovieUsecase: GetDetailMovieUsecase
    @Mock private lateinit var getDetailTvUsecase: GetDetailTvUsecase
    @Mock private lateinit var movieObserver: Observer<Resource<Movie>>
    @Mock private lateinit var tvObserver: Observer<Resource<TvShow>>

    private lateinit var viewModel: DetailViewModel

    private val moviePosition = 0
    private val tvShowPosition = 0

    private val dummyMovieList = DataStore.movies
    private val dummyTvShowList = DataStore.tvShowList

    private val dummyMovie = dummyMovieList[moviePosition]
    private val dummyTvShow = dummyTvShowList[tvShowPosition]

    private val movieId = dummyMovie.id
    private val tvShowId = dummyTvShow.id

    @Before
    fun setUp() {
        viewModel = DetailViewModel(getDetailMovieUsecase, getDetailTvUsecase)
        viewModel.setSelectedContent(movieId)
        viewModel.setSelectedContent(tvShowId)
    }

    @Test
    fun getMovie() = runBlockingTest {
        viewModel.setSelectedContent(movieId)
        val resource: Resource<Movie> = Resource.Success(dummyMovie)
        val liveData = LiveDataTestUtil.setValue(resource)
        Mockito.`when`(getDetailMovieUsecase.run(GetDetailMovieUsecase.Params(movieId))).thenReturn(liveData)
        val movie = viewModel.getMovie().value
        Mockito.verify(getDetailMovieUsecase).run(GetDetailMovieUsecase.Params(movieId))

        assertNotNull(movie)
        when(movie) {
            is Resource.Success -> {
                assertEquals(dummyMovie.id, movie.data.id)
                assertEquals(dummyMovie.title, movie.data.title)
                assertEquals(dummyMovie.storyLine, movie.data.storyLine)
                assertEquals(dummyMovie.genre, movie.data.genre)
                assertEquals(dummyMovie.releaseDate, movie.data.releaseDate)
                assertEquals(dummyMovie.imagePosterUrl, movie.data.imagePosterUrl)
            }
        }

        viewModel.getMovie().observeForever(movieObserver)
        Mockito.verify(movieObserver).onChanged(resource)
    }

    @Test
    fun getTvShow() = runBlockingTest {
        viewModel.setSelectedContent(tvShowId)
        val resource: Resource<TvShow> = Resource.Success(dummyTvShow)
        val liveData = LiveDataTestUtil.setValue(resource)
        Mockito.`when`(getDetailTvUsecase.run(GetDetailTvUsecase.Params(tvShowId))).thenReturn(liveData)
        val tvShow = viewModel.getTvShow().value
        Mockito.verify(getDetailTvUsecase).run(GetDetailTvUsecase.Params(tvShowId))

        assertNotNull(tvShow)
        when(tvShow) {
            is Resource.Success -> {
                assertEquals(dummyTvShow.id, tvShow.data.id)
                assertEquals(dummyTvShow.title, tvShow.data.title)
                assertEquals(dummyTvShow.storyLine, tvShow.data.storyLine)
                assertEquals(dummyTvShow.genre, tvShow.data.genre)
                assertEquals(dummyTvShow.releaseDate, tvShow.data.releaseDate)
                assertEquals(dummyTvShow.imagePosterUrl, tvShow.data.imagePosterUrl)
            }
        }

        viewModel.getTvShow().observeForever(tvObserver)
        Mockito.verify(tvObserver).onChanged(resource)
    }
}