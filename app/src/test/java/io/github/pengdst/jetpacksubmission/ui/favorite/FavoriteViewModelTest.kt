package io.github.pengdst.jetpacksubmission.ui.favorite

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.google.common.truth.Truth
import io.github.pengdst.jetpacksubmission.domain.models.Movie
import io.github.pengdst.jetpacksubmission.domain.models.TvShow
import io.github.pengdst.jetpacksubmission.domain.usecase.GetBookmarkedMoviesUsecase
import io.github.pengdst.jetpacksubmission.domain.usecase.GetBookmarkedTvShowsUsecase
import io.github.pengdst.jetpacksubmission.utils.DataStore
import io.github.pengdst.jetpacksubmission.utils.LiveDataTestUtil
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner

/**
 * Created on 6/4/21 by Pengkuh Dwi Septiandi (@pengdst)
 *
 * - Github https://github.com/pengdst
 * - Gitlab https://gitlab.com/pengdst
 * - LinkedIn https://linkedin.com/in/pengdst
 */

@RunWith(MockitoJUnitRunner::class)
class FavoriteViewModelTest {

    @get:Rule var instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var viewModel: FavoriteViewModel

    @Mock private lateinit var getBookmarkedMovieUsecase: GetBookmarkedMoviesUsecase
    @Mock private lateinit var getBookmarkedTvShowsUsecase: GetBookmarkedTvShowsUsecase

    @Mock private lateinit var movieListObserver: Observer<List<Movie>>
    @Mock private lateinit var tvListObserver: Observer<List<TvShow>>

    private val dummyMovieList = DataStore.movies
    private val dummyTvShowList = DataStore.tvShowList

    @Before
    fun setUp() {
        viewModel = FavoriteViewModel(getBookmarkedMovieUsecase, getBookmarkedTvShowsUsecase)
    }

    @Test
    fun getBookmarkedMovies() {
        val liveData = LiveDataTestUtil.setValue(dummyMovieList)
        Mockito.`when`(getBookmarkedMovieUsecase.run(GetBookmarkedMoviesUsecase.Companion)).thenReturn(liveData)
        val movies = viewModel.getMovies().value
        Mockito.verify(getBookmarkedMovieUsecase).run(GetBookmarkedMoviesUsecase.Companion)

        Truth.assertThat(movies).isNotNull()
        Truth.assertThat(movies).isNotEmpty()
        Truth.assertThat(movies).hasSize(dummyMovieList.size)
        Truth.assertThat(movies).isEqualTo(dummyMovieList)

        viewModel.getMovies().observeForever(movieListObserver)
        Mockito.verify(movieListObserver).onChanged(dummyMovieList)
    }

    @Test
    fun getBookmarkedTvShowList() {
        val liveData = LiveDataTestUtil.setValue(dummyTvShowList)
        Mockito.`when`(getBookmarkedTvShowsUsecase.run(GetBookmarkedTvShowsUsecase.Companion)).thenReturn(liveData)
        val tvShow = viewModel.getTvShowList().value
        Mockito.verify(getBookmarkedTvShowsUsecase).run(GetBookmarkedTvShowsUsecase.Companion)

        Truth.assertThat(tvShow).isNotNull()
        Truth.assertThat(tvShow).isNotEmpty()
        Truth.assertThat(tvShow).hasSize(dummyTvShowList.size)
        Truth.assertThat(tvShow).isEqualTo(dummyTvShowList)

        viewModel.getTvShowList().observeForever(tvListObserver)
        Mockito.verify(tvListObserver).onChanged(dummyTvShowList)
    }
}