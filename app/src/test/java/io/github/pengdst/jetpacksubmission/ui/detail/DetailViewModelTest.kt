package io.github.pengdst.jetpacksubmission.ui.detail

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.google.common.truth.Truth
import io.github.pengdst.jetpacksubmission.data.vo.Resource
import io.github.pengdst.jetpacksubmission.domain.models.Movie
import io.github.pengdst.jetpacksubmission.domain.models.TvShow
import io.github.pengdst.jetpacksubmission.domain.usecase.GetDetailMovieUsecase
import io.github.pengdst.jetpacksubmission.domain.usecase.GetDetailTvUsecase
import io.github.pengdst.jetpacksubmission.domain.usecase.SetBookmarkedMovieUsecase
import io.github.pengdst.jetpacksubmission.domain.usecase.SetBookmarkedTvShowUsecase
import io.github.pengdst.jetpacksubmission.utils.DataStore
import io.github.pengdst.jetpacksubmission.utils.LiveDataTestUtil
import io.github.pengdst.jetpacksubmission.utils.MainCoroutineRule
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
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
    @Mock private lateinit var setBookmarkedMovieUsecase: SetBookmarkedMovieUsecase
    @Mock private lateinit var setBookmarkedTvShowsUsecase: SetBookmarkedTvShowUsecase

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
        viewModel = DetailViewModel(getDetailMovieUsecase, getDetailTvUsecase, setBookmarkedMovieUsecase, setBookmarkedTvShowsUsecase)
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

        Truth.assertThat(movie).isNotNull()
        Truth.assertThat(movie).isInstanceOf(Resource.Success::class.java)

        movie as Resource.Success
        Truth.assertThat(movie.data).isNotNull()
        Truth.assertThat(movie.data.id).isEqualTo(dummyMovie.id)
        Truth.assertThat(movie.data.title).isEqualTo(dummyMovie.title)
        Truth.assertThat(movie.data.storyLine).isEqualTo(dummyMovie.storyLine)
        Truth.assertThat(movie.data.genre).isEqualTo(dummyMovie.genre)
        Truth.assertThat(movie.data.releaseDate).isEqualTo(dummyMovie.releaseDate)
        Truth.assertThat(movie.data.imagePosterUrl).isEqualTo(dummyMovie.imagePosterUrl)

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

        Truth.assertThat(tvShow).isNotNull()
        Truth.assertThat(tvShow).isInstanceOf(Resource.Success::class.java)

        tvShow as Resource.Success
        Truth.assertThat(tvShow.data).isNotNull()
        Truth.assertThat(tvShow.data.id).isEqualTo(dummyTvShow.id)
        Truth.assertThat(tvShow.data.title).isEqualTo(dummyTvShow.title)
        Truth.assertThat(tvShow.data.storyLine).isEqualTo(dummyTvShow.storyLine)
        Truth.assertThat(tvShow.data.genre).isEqualTo(dummyTvShow.genre)
        Truth.assertThat(tvShow.data.releaseDate).isEqualTo(dummyTvShow.releaseDate)
        Truth.assertThat(tvShow.data.imagePosterUrl).isEqualTo(dummyTvShow.imagePosterUrl)

        viewModel.getTvShow().observeForever(tvObserver)
        Mockito.verify(tvObserver).onChanged(resource)
    }
}