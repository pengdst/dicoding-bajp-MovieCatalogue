package io.github.pengdst.jetpacksubmission.data.repository

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.google.common.truth.Truth
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
import io.github.pengdst.jetpacksubmission.utils.LiveDataTestUtil.getOrAwaitValue
import io.github.pengdst.jetpacksubmission.utils.MainCoroutineRule
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
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

    @get:Rule var instantTaskExecutorRule = InstantTaskExecutorRule()
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

        val movieList = repository.getUpcomingMovies().getOrAwaitValue(waitUntilFinished = true)

        Truth.assertThat(movieList).isNotNull()
        Truth.assertThat(movieList).isInstanceOf(Resource.Success::class.java)

        movieList as Resource.Success
        Truth.assertThat(movieList.data.size).isEqualTo(dummyMovieList.size)
        Truth.assertThat(movieList.data).isEqualTo(dummyMovieList)
    }

    @Test
    fun getMovie() = runBlockingTest {
        val dummyResponseSuccess = Response.success(dummyMovieResponses[0])
        val entityLiveData = LiveDataTestUtil.setValue(dummyMovie.toEntity())

        Mockito.`when`(local.getMovie(movieId)).thenReturn(entityLiveData)
        Mockito.`when`(remote.getMovie(movieId)).thenReturn(dummyResponseSuccess)

        val movie = repository.getMovie(movieId).getOrAwaitValue(waitUntilFinished = true)
        verify(remote).getMovie(movieId)

        Truth.assertThat(movie).isNotNull()
        Truth.assertThat(movie).isInstanceOf(Resource.Success::class.java)

        movie as Resource.Success
        Truth.assertThat(movie.data.id).isEqualTo(dummyMovie.id)
        Truth.assertThat(movie.data.title).isEqualTo(dummyMovie.title)
        Truth.assertThat(movie.data.storyLine).isEqualTo(dummyMovie.storyLine)
        Truth.assertThat(movie.data.genre).isEqualTo(dummyMovie.genre)
        Truth.assertThat(movie.data.releaseDate).isEqualTo(dummyMovie.releaseDate)
        Truth.assertThat(movie.data.imagePosterUrl).isEqualTo(dummyMovie.imagePosterUrl)
    }

    @Test
    fun getTvOnAir() = runBlockingTest {
        val entityLiveData = LiveDataTestUtil.setValue(dummyTvList.map { it.toEntity() })
        Mockito.`when`(local.getTvShows()).thenReturn(entityLiveData)

        val tvList = repository.getTvOnAir().getOrAwaitValue(waitUntilFinished = true)

        Truth.assertThat(tvList).isNotNull()
        Truth.assertThat(tvList).isInstanceOf(Resource.Success::class.java)

        tvList as Resource.Success
        Truth.assertThat(tvList.data.size).isEqualTo(dummyTvList.size)
        Truth.assertThat(tvList.data).isEqualTo(dummyTvList)
    }

    @Test
    fun getTv() = runBlockingTest {
        val dummyResponseSuccess = Response.success(dummyTvListResponses[0])
        val entityLiveData = LiveDataTestUtil.setValue(dummyTvShow.toEntity())

        Mockito.`when`(local.getTv(tvId)).thenReturn(entityLiveData)
        Mockito.`when`(remote.getTv(tvId)).thenReturn(dummyResponseSuccess)

        val tvShow = repository.getTv(tvId).getOrAwaitValue(waitUntilFinished = true)
        verify(remote).getTv(tvId)

        Truth.assertThat(tvShow).isNotNull()
        Truth.assertThat(tvShow).isInstanceOf(Resource.Success::class.java)

        tvShow as Resource.Success
        Truth.assertThat(tvShow.data.id).isEqualTo(dummyTvShow.id)
        Truth.assertThat(tvShow.data.title).isEqualTo(dummyTvShow.title)
        Truth.assertThat(tvShow.data.storyLine).isEqualTo(dummyTvShow.storyLine)
        Truth.assertThat(tvShow.data.genre).isEqualTo(dummyTvShow.genre)
        Truth.assertThat(tvShow.data.releaseDate).isEqualTo(dummyTvShow.releaseDate)
        Truth.assertThat(tvShow.data.imagePosterUrl).isEqualTo(dummyTvShow.imagePosterUrl)
    }
}