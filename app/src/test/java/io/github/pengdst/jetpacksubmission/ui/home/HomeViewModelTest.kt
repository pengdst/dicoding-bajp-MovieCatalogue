package io.github.pengdst.jetpacksubmission.ui.home

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import io.github.pengdst.jetpacksubmission.data.repository.MovieRepositoryImpl
import io.github.pengdst.jetpacksubmission.data.source.domain.models.Movie
import io.github.pengdst.jetpacksubmission.data.source.domain.models.TvShow
import io.github.pengdst.jetpacksubmission.utils.DataStore
import io.github.pengdst.jetpacksubmission.utils.LiveDataTestUtil
import junit.framework.TestCase
import org.junit.Assert
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

@RunWith(MockitoJUnitRunner::class)
class HomeViewModelTest : TestCase() {

    @get:Rule var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock private lateinit var repository: MovieRepositoryImpl
    @Mock private lateinit var movieListObserver: Observer<List<Movie>>
    @Mock private lateinit var tvListObserver: Observer<List<TvShow>>

    private lateinit var viewModel: HomeViewModel

    val dummyMovieList = DataStore.movies
    val dummyTvShowList = DataStore.tvShowList

    @Before
    public override fun setUp() {
        viewModel = HomeViewModel(repository)
    }

    @Test
    fun testGetMovies() {
        val lMovies = MutableLiveData(dummyMovieList)

        Mockito.`when`(repository.getUpcomingMovies()).thenReturn(lMovies)
        val movies = LiveDataTestUtil.getValue(viewModel.getMovies())
        Mockito.verify(repository).getUpcomingMovies()

        Assert.assertNotNull(movies)
        Assert.assertNotEquals(0, movies.size)
        Assert.assertEquals(DataStore.movies.size, movies.size)

        viewModel.getMovies().observeForever(movieListObserver)
        Mockito.verify(movieListObserver).onChanged(dummyMovieList)
    }

    @Test
    fun testGetTvShowList() {
        val lTvList = MutableLiveData(dummyTvShowList)

        Mockito.`when`(repository.getTvOnAir()).thenReturn(lTvList)
        val tvShow = LiveDataTestUtil.getValue(viewModel.getTvShowList())
        Mockito.verify(repository).getTvOnAir()

        Assert.assertNotNull(tvShow)
        Assert.assertNotEquals(0, tvShow.size)
        Assert.assertEquals(DataStore.tvShowList.size, tvShow.size)

        viewModel.getTvShowList().observeForever(tvListObserver)
        Mockito.verify(tvListObserver).onChanged(dummyTvShowList)
    }
}