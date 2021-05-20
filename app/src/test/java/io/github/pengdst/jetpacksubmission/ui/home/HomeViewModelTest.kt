package io.github.pengdst.jetpacksubmission.ui.home

import io.github.pengdst.jetpacksubmission.utils.DataStore
import junit.framework.TestCase
import org.junit.Assert
import org.junit.Before
import org.junit.Test

/**
 * Created on 5/17/21 by Pengkuh Dwi Septiandi (@pengdst)
 *
 * - Github https://github.com/pengdst
 * - Gitlab https://gitlab.com/pengdst
 * - LinkedIn https://linkedin.com/in/pengdst
 */
class HomeViewModelTest : TestCase() {

    private lateinit var viewModel: HomeViewModel

    @Before
    public override fun setUp() {
        viewModel = HomeViewModel(FakeRepository())
    }

    @Test
    fun testGetMovies() {
        val movies = viewModel.getMovies().value
        Assert.assertNotNull(movies)
        Assert.assertNotEquals(0, movies?.size)
        Assert.assertEquals(DataStore.movies.size, movies?.size)
    }

    @Test
    fun testGetTvShowList() {
        val tvShow = viewModel.getTvShowList().value
        Assert.assertNotNull(tvShow)
        Assert.assertNotEquals(0, tvShow?.size)
        Assert.assertEquals(DataStore.tvShowList.size, tvShow?.size)
    }
}