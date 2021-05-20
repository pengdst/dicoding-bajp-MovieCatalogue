package io.github.pengdst.jetpacksubmission.ui.detail

import io.github.pengdst.jetpacksubmission.ui.home.FakeRepository
import io.github.pengdst.jetpacksubmission.utils.DataStore
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Test

/**
 * Created on 5/17/21 by Pengkuh Dwi Septiandi (@pengdst)
 *
 * - Github https://github.com/pengdst
 * - Gitlab https://gitlab.com/pengdst
 * - LinkedIn https://linkedin.com/in/pengdst
 */
class DetailViewModelTest {

    private lateinit var viewModel: DetailViewModel
    private val moviePosition = 0
    private val tvShowPosition = 0
    private val dummyMovie = DataStore.movies[moviePosition]
    private val dummyTvShow = DataStore.tvShowList[tvShowPosition]
    private val movieId = dummyMovie.id
    private val tvShowId = dummyTvShow.id

    @Before
    fun setUp() {
        viewModel = DetailViewModel(FakeRepository())
        viewModel.setSelectedContent(movieId)
        viewModel.setSelectedContent(tvShowId)
    }

    @Test
    fun getMovie() {
        val movie = viewModel.getMovie().value
        assertNotNull(movie)
        assertEquals(dummyMovie.id, movie?.id)
        assertEquals(dummyMovie.title, movie?.title)
        assertEquals(dummyMovie.storyLine, movie?.storyLine)
        assertEquals(dummyMovie.genre, movie?.genre)
        assertEquals(dummyMovie.releaseDate, movie?.releaseDate)
        assertEquals(dummyMovie.imagePosterUrl, movie?.imagePosterUrl)
    }

    @Test
    fun getTvShow() {
        val tvShow = viewModel.getTvShow().value
        assertNotNull(tvShow)
        assertEquals(dummyTvShow.id, tvShow?.id)
        assertEquals(dummyTvShow.title, tvShow?.title)
        assertEquals(dummyTvShow.storyLine, tvShow?.storyLine)
        assertEquals(dummyTvShow.genre, tvShow?.genre)
        assertEquals(dummyTvShow.releaseDate, tvShow?.releaseDate)
        assertEquals(dummyTvShow.imagePosterUrl, tvShow?.imagePosterUrl)
    }
}