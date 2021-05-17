package io.github.pengdst.jetpacksubmission.ui.detail

import io.github.pengdst.jetpacksubmission.utils.DataStore
import org.junit.Before
import org.junit.Test

import org.junit.Assert.*

/**
 * Created on 5/17/21 by Pengkuh Dwi Septiandi (@pengdst)
 *
 * - Github https://github.com/pengdst
 * - Gitlab https://gitlab.com/pengdst
 * - LinkedIn https://linkedin.com/in/pengdst
 */
class DetailViewModelTest {

    private lateinit var viewModel: DetailViewModel
    private val dummyMovie = DataStore.movies[0]
    private val dummyTvShow = DataStore.tvShowList[0]
    private val moviePosition = 0
    private val tvShowPosition = 0

    @Before
    fun setUp() {
        viewModel = DetailViewModel()
        viewModel.setSelectedContent(moviePosition)
        viewModel.setSelectedContent(tvShowPosition)
    }

    @Test
    fun getMovie() {
        val movie = viewModel.getMovie()
        assertNotNull(movie)
        assertEquals(dummyMovie.id, movie.id)
        assertEquals(dummyMovie.title, movie.title)
        assertEquals(dummyMovie.storyLine, movie.storyLine)
        assertEquals(dummyMovie.genre, movie.genre)
        assertEquals(dummyMovie.releaseDate, movie.releaseDate)
        assertEquals(dummyMovie.imageUrl, movie.imageUrl)
    }

    @Test
    fun getTvShow() {
        val tvShow = viewModel.getTvShow()
        assertNotNull(tvShow)
        assertEquals(dummyTvShow.id, tvShow.id)
        assertEquals(dummyTvShow.title, tvShow.title)
        assertEquals(dummyTvShow.storyLine, tvShow.storyLine)
        assertEquals(dummyTvShow.genre, tvShow.genre)
        assertEquals(dummyTvShow.releaseDate, tvShow.releaseDate)
        assertEquals(dummyTvShow.imageUrl, tvShow.imageUrl)
    }
}