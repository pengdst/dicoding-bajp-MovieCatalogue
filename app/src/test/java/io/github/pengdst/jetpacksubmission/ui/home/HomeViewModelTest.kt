package io.github.pengdst.jetpacksubmission.ui.home

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.google.common.truth.Truth
import io.github.pengdst.jetpacksubmission.data.vo.Resource
import io.github.pengdst.jetpacksubmission.domain.models.Movie
import io.github.pengdst.jetpacksubmission.domain.models.TvShow
import io.github.pengdst.jetpacksubmission.domain.usecase.GetTvOnAirUsecase
import io.github.pengdst.jetpacksubmission.domain.usecase.GetUpcomingMoviesUsecase
import io.github.pengdst.jetpacksubmission.utils.DataStore
import io.github.pengdst.jetpacksubmission.utils.LiveDataTestUtil
import io.github.pengdst.jetpacksubmission.utils.MainCoroutineRule
import junit.framework.TestCase
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
class HomeViewModelTest : TestCase() {

    @get:Rule var instantTaskExecutorRule = InstantTaskExecutorRule()
    @get:Rule var mainCoroutineRule = MainCoroutineRule()

    @Mock private lateinit var getTvOnAirUsecase: GetTvOnAirUsecase
    @Mock private lateinit var getUpcomingMoviesUsecase: GetUpcomingMoviesUsecase
    @Mock private lateinit var movieListObserver: Observer<Resource<List<Movie>>>
    @Mock private lateinit var tvListObserver: Observer<Resource<List<TvShow>>>

    private lateinit var viewModel: HomeViewModel

    private val dummyMovieList = DataStore.movies
    private val dummyTvShowList = DataStore.tvShowList

    @Before
    public override fun setUp() {
        viewModel = HomeViewModel(getUpcomingMoviesUsecase, getTvOnAirUsecase)
    }

    @Test
    fun testGetMovies() = runBlockingTest {
        val resource: Resource<List<Movie>> = Resource.Success(dummyMovieList)
        val liveData = LiveDataTestUtil.setValue(resource)
        Mockito.`when`(getUpcomingMoviesUsecase.run(GetUpcomingMoviesUsecase.Companion)).thenReturn(liveData)
        val movies = viewModel.getMovies().value
        Mockito.verify(getUpcomingMoviesUsecase).run(GetUpcomingMoviesUsecase.Companion)

        viewModel.getMovies().observeForever(movieListObserver)
        Mockito.verify(movieListObserver).onChanged(resource)

        Truth.assertThat(movies).isNotNull()
        Truth.assertThat(movies).isInstanceOf(Resource.Success::class.java)
        Truth.assertThat(movies).isEqualTo(resource)

        movies as Resource.Success
        Truth.assertThat(movies.data.size).isEqualTo(dummyMovieList.size)
        Truth.assertThat(movies.data).isEqualTo(dummyMovieList)
    }

    @Test
    fun testGetTvShowList() = runBlockingTest {
        val resource: Resource<List<TvShow>> = Resource.Success(dummyTvShowList)
        val liveData = LiveDataTestUtil.setValue(resource)
        Mockito.`when`(getTvOnAirUsecase.run(GetTvOnAirUsecase.Companion)).thenReturn(liveData)
        val tvShow = viewModel.getTvShowList().value
        Mockito.verify(getTvOnAirUsecase).run(GetTvOnAirUsecase.Companion)

        viewModel.getTvShowList().observeForever(tvListObserver)
        Mockito.verify(tvListObserver).onChanged(resource)
        Truth.assertThat(tvShow).isNotNull()
        Truth.assertThat(tvShow).isInstanceOf(Resource.Success::class.java)
        Truth.assertThat(tvShow).isEqualTo(resource)

        tvShow as Resource.Success
        Truth.assertThat(tvShow.data.size).isEqualTo(dummyTvShowList.size)
        Truth.assertThat(tvShow.data).isEqualTo(dummyTvShowList)

    }
}