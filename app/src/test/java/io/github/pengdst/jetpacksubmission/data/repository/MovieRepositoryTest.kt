package io.github.pengdst.jetpacksubmission.data.repository

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.nhaarman.mockitokotlin2.verify
import io.github.pengdst.jetpacksubmission.data.local.mapper.MovieEntityMapper.toDomain
import io.github.pengdst.jetpacksubmission.data.local.mapper.MovieEntityMapper.toEntity
import io.github.pengdst.jetpacksubmission.data.local.mapper.TvShowEntityMapper.toDomain
import io.github.pengdst.jetpacksubmission.data.local.mapper.TvShowEntityMapper.toEntity
import io.github.pengdst.jetpacksubmission.data.local.source.MovieLocalSource
import io.github.pengdst.jetpacksubmission.data.remote.mapper.MovieDtoMapper.toEntity
import io.github.pengdst.jetpacksubmission.data.remote.mapper.TvDtoMapper.toEntity
import io.github.pengdst.jetpacksubmission.data.remote.source.MovieRemoteSource
import io.github.pengdst.jetpacksubmission.data.vo.Resource
import io.github.pengdst.jetpacksubmission.utils.DataStore
import io.github.pengdst.jetpacksubmission.utils.LiveDataTestUtil
import io.github.pengdst.jetpacksubmission.utils.MainCoroutineRule
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
    @get:Rule var mainCoroutineRule = MainCoroutineRule()

    private val remote = Mockito.mock(MovieRemoteSource::class.java)
    private val local = Mockito.mock(MovieLocalSource::class.java)
    private val repository = MovieRepositoryImpl(remote, local)

    private val dummyTvListResponses = DataStore.tvShowListResponse
    private val dummyMovieResponses = DataStore.moviesResponse

    private val dummyTvList = dummyTvListResponses.toEntity().toDomain()
    private val dummyMovieList = dummyMovieResponses.toEntity().toDomain()

    private val dummyTvShow = dummyTvList[0]
    private val dummyMovie = dummyMovieList[0]

    private val tvId = dummyTvShow.id
    private val movieId = dummyMovie.id

    @Test
    fun getUpcomingMovies() = runBlockingTest {
        val entityLiveData = LiveDataTestUtil.setValue(dummyMovieList.map { it.toEntity() })
        Mockito.`when`(local.getAllMovies()).thenReturn(entityLiveData)

        val movieList = LiveDataTestUtil.getValue(repository.getUpcomingMovies())

        Assert.assertNotNull(movieList)
        when(movieList) {
            is Resource.Success -> Assert.assertEquals(dummyMovieList.size, movieList.data.size)
        }
    }

    @Test
    fun getMovie() = runBlockingTest {
        val dummyResponseSuccess = Response.success(dummyMovieResponses[0])
        val entityLiveData = LiveDataTestUtil.setValue(dummyMovie.toEntity())
        Mockito.`when`(local.getMovie(movieId)).thenReturn(entityLiveData)
        Mockito.`when`(remote.getMovie(movieId)).thenReturn(dummyResponseSuccess)
        val movie = LiveDataTestUtil.getValue(repository.getMovie(movieId))
        verify(remote).getMovie(movieId)

        Assert.assertNotNull(movie)
        when(movie) {
            is Resource.Success -> {
                Assert.assertEquals(dummyMovie.id, movie.data.id)
                Assert.assertEquals(dummyMovie.title, movie.data.title)
                Assert.assertEquals(dummyMovie.storyLine, movie.data.storyLine)
                Assert.assertEquals(dummyMovie.genre, movie.data.genre)
                Assert.assertEquals(dummyMovie.releaseDate, movie.data.releaseDate)
                Assert.assertEquals(dummyMovie.imagePosterUrl, movie.data.imagePosterUrl)
            }
        }
    }

    @Test
    fun getTvOnAir() = runBlockingTest {
        val entityLiveData = LiveDataTestUtil.setValue(dummyTvList.map { it.toEntity() })
        Mockito.`when`(local.getTvShows()).thenReturn(entityLiveData)

        val tvList = LiveDataTestUtil.getValue(repository.getTvOnAir())

        Assert.assertNotNull(tvList)
        when(tvList) {
            is Resource.Success -> Assert.assertEquals(dummyTvList.size, tvList.data.size)
        }
    }

    @Test
    fun getTv() = runBlockingTest {
        val dummyResponseSuccess = Response.success(dummyTvListResponses[0])
        val entityLiveData = LiveDataTestUtil.setValue(dummyTvShow.toEntity())
        Mockito.`when`(local.getTv(tvId)).thenReturn(entityLiveData)
        Mockito.`when`(remote.getTv(tvId)).thenReturn(dummyResponseSuccess)
        val tvShow = LiveDataTestUtil.getValue(repository.getTv(tvId))
        verify(remote).getTv(tvId)

        Assert.assertNotNull(tvShow)
        when(tvShow) {
            is Resource.Success -> {
                Assert.assertEquals(dummyTvShow.id, tvShow.data.id)
                Assert.assertEquals(dummyTvShow.title, tvShow.data.title)
                Assert.assertEquals(dummyTvShow.storyLine, tvShow.data.storyLine)
                Assert.assertEquals(dummyTvShow.genre, tvShow.data.genre)
                Assert.assertEquals(dummyTvShow.releaseDate, tvShow.data.releaseDate)
                Assert.assertEquals(dummyTvShow.imagePosterUrl, tvShow.data.imagePosterUrl)
            }
        }
    }
}