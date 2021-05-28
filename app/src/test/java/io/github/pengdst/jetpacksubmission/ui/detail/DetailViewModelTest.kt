package io.github.pengdst.jetpacksubmission.ui.detail

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import io.github.pengdst.jetpacksubmission.data.repository.MovieRepositoryImpl
import io.github.pengdst.jetpacksubmission.domain.models.Movie
import io.github.pengdst.jetpacksubmission.domain.models.TvShow
import io.github.pengdst.jetpacksubmission.utils.DataStore
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

    @Mock private lateinit var repository: MovieRepositoryImpl
    @Mock private lateinit var movieObserver: Observer<Movie>
    @Mock private lateinit var tvObserver: Observer<TvShow>

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
        viewModel = DetailViewModel(repository)
        viewModel.setSelectedContent(movieId)
        viewModel.setSelectedContent(tvShowId)
    }

    @Test
    fun getMovie() = runBlockingTest {
        viewModel.setSelectedContent(movieId)
        Mockito.`when`(repository.getMovie(movieId)).thenReturn(dummyMovie)
        val movie = viewModel.getMovie().value
        Mockito.verify(repository).getMovie(movieId)

        assertNotNull(movie)
        assertEquals(dummyMovie.id, movie?.id)
        assertEquals(dummyMovie.title, movie?.title)
        assertEquals(dummyMovie.storyLine, movie?.storyLine)
        assertEquals(dummyMovie.genre, movie?.genre)
        assertEquals(dummyMovie.releaseDate, movie?.releaseDate)
        assertEquals(dummyMovie.imagePosterUrl, movie?.imagePosterUrl)

        viewModel.getMovie().observeForever(movieObserver)
        Mockito.verify(movieObserver).onChanged(dummyMovie)
    }

    @Test
    fun getTvShow() = runBlockingTest {
        viewModel.setSelectedContent(tvShowId)
        Mockito.`when`(repository.getTv(tvShowId)).thenReturn(dummyTvShow)
        val tvShow = viewModel.getTvShow().value
        Mockito.verify(repository).getTv(tvShowId)

        assertNotNull(tvShow)
        assertEquals(dummyTvShow.id, tvShow?.id)
        assertEquals(dummyTvShow.title, tvShow?.title)
        assertEquals(dummyTvShow.storyLine, tvShow?.storyLine)
        assertEquals(dummyTvShow.genre, tvShow?.genre)
        assertEquals(dummyTvShow.releaseDate, tvShow?.releaseDate)
        assertEquals(dummyTvShow.imagePosterUrl, tvShow?.imagePosterUrl)

        viewModel.getTvShow().observeForever(tvObserver)
        Mockito.verify(tvObserver).onChanged(dummyTvShow)
    }
}