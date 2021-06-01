package io.github.pengdst.jetpacksubmission.data.repository

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.nhaarman.mockitokotlin2.verify
import io.github.pengdst.jetpacksubmission.data.remote.source.MovieRemoteSource
import io.github.pengdst.jetpacksubmission.data.remote.mapper.MovieMapper.toDomain
import io.github.pengdst.jetpacksubmission.data.remote.mapper.TvMapper.toDomain
import io.github.pengdst.jetpacksubmission.data.remote.retrofit.response.MovieResponse
import io.github.pengdst.jetpacksubmission.data.remote.retrofit.response.TvResponse
import io.github.pengdst.jetpacksubmission.utils.DataStore
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito
import retrofit2.Response

/**
 * Created on 5/20/21 by Pengkuh Dwi Septiandi (@pengdst)
 *
 * - Github https://github.com/pengdst
 * - Gitlab https://gitlab.com/pengdst
 * - LinkedIn https://linkedin.com/in/pengdst
 */
@ExperimentalCoroutinesApi
class MovieRepositoryTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private val remote = Mockito.mock(MovieRemoteSource::class.java)
    private val repository = MovieRepositoryImpl(remote)

    private val dummyTvListResponses = DataStore.tvShowListResponse
    private val dummyMovieResponses = DataStore.moviesResponse

    private val dummyTvList = dummyTvListResponses.toDomain()
    private val dummyMovieList = dummyMovieResponses.toDomain()

    private val dummyTvShow = dummyTvList[0]
    private val dummyMovie = dummyMovieList[0]

    private val tvId = dummyTvShow.id
    private val movieId = dummyMovie.id

    @Test
    fun getUpcomingMovies() = runBlockingTest {
        val dummyResponseSuccess = Response.success(MovieResponse(results = dummyMovieResponses))
        Mockito.`when`(remote.getUpcomingMovies()).thenReturn(dummyResponseSuccess)
        val movieList = repository.getUpcomingMovies()
        verify(remote).getUpcomingMovies()

        Assert.assertNotNull(movieList)
        Assert.assertEquals(dummyMovieList.size, movieList?.size)
    }

    @Test
    fun getMovie() = runBlockingTest {
        val dummyResponseSuccess = Response.success(dummyMovieResponses[0])
        Mockito.`when`(remote.getMovie(movieId)).thenReturn(dummyResponseSuccess)
        val movie = repository.getMovie(movieId)
        verify(remote).getMovie(movieId)

        Assert.assertNotNull(movie)
        Assert.assertEquals(dummyMovie.id, movie?.id)
        Assert.assertEquals(dummyMovie.title, movie?.title)
        Assert.assertEquals(dummyMovie.storyLine, movie?.storyLine)
        Assert.assertEquals(dummyMovie.genre, movie?.genre)
        Assert.assertEquals(dummyMovie.releaseDate, movie?.releaseDate)
        Assert.assertEquals(dummyMovie.imagePosterUrl, movie?.imagePosterUrl)
    }

    @Test
    fun getTvOnAir() = runBlockingTest {
        val dummyResponseSuccess = Response.success(TvResponse(results = dummyTvListResponses))
        Mockito.`when`(remote.getTvOnAir()).thenReturn(dummyResponseSuccess)

        val tvList = repository.getTvOnAir()
        verify(remote).getTvOnAir()

        Assert.assertNotNull(tvList)
        Assert.assertEquals(dummyTvList.size, tvList?.size)
    }

    @Test
    fun getTv() = runBlockingTest {
        val dummyResponseSuccess = Response.success(dummyTvListResponses[0])
        Mockito.`when`(remote.getTv(tvId)).thenReturn(dummyResponseSuccess)
        val tvShow = repository.getTv(tvId)
        verify(remote).getTv(tvId)

        Assert.assertNotNull(tvShow)
        Assert.assertEquals(dummyTvShow.id, tvShow?.id)
        Assert.assertEquals(dummyTvShow.title, tvShow?.title)
        Assert.assertEquals(dummyTvShow.storyLine, tvShow?.storyLine)
        Assert.assertEquals(dummyTvShow.genre, tvShow?.genre)
        Assert.assertEquals(dummyTvShow.releaseDate, tvShow?.releaseDate)
        Assert.assertEquals(dummyTvShow.imagePosterUrl, tvShow?.imagePosterUrl)
    }
}