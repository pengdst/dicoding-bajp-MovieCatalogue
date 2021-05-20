package io.github.pengdst.jetpacksubmission.data.repository

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.doAnswer
import com.nhaarman.mockitokotlin2.eq
import com.nhaarman.mockitokotlin2.verify
import io.github.pengdst.jetpacksubmission.data.source.domain.models.Movie
import io.github.pengdst.jetpacksubmission.data.source.domain.models.TvShow
import io.github.pengdst.jetpacksubmission.data.source.remote.MovieRemoteSource
import io.github.pengdst.jetpacksubmission.utils.DataStore
import io.github.pengdst.jetpacksubmission.utils.LiveDataTestUtil
import io.github.pengdst.jetpacksubmission.utils.LoadContent
import org.junit.Assert
import org.junit.Rule
import org.junit.Test

import org.mockito.Mockito

/**
 * Created on 5/20/21 by Pengkuh Dwi Septiandi (@pengdst)
 *
 * - Github https://github.com/pengdst
 * - Gitlab https://gitlab.com/pengdst
 * - LinkedIn https://linkedin.com/in/pengdst
 */
class MovieRepositoryTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private val remote = Mockito.mock(MovieRemoteSource::class.java)
    private val repository = MovieRepositoryImpl(remote)

    private val dummyTvListResponses = DataStore.tvShowListResponse
    private val dummyMovieResponses = DataStore.moviesResponse

    private val dummyTvList = dummyTvListResponses.map {data->
        TvShow(
            id = data.id.toString(),
            title = data.name.toString(),
            backdropPath = data.backdropPath.toString(),
            posterPath = data.posterPath.toString(),
            releaseDate = data.firstAirDate.toString(),
            language = data.spokenLanguages?.map { it.englishName }.toString()
                .replace("[", "").replace("]", ""),
            genre = data.genres?.map { it.name }.toString().replace("[", "")
                .replace("]", ""),
            storyLine = data.overview.toString()
        )
    }
    private val dummyMovieList = dummyMovieResponses.map { data ->
        Movie(
            id = data.id.toString(),
            title = data.title.toString(),
            backdropPath = data.backdropPath.toString(),
            posterPath = data.posterPath.toString(),
            releaseDate = data.releaseDate.toString(),
            language = data.spokenLanguages?.map { it.englishName }.toString()
                .replace("[", "").replace("]", ""),
            genre = data.genres?.map { it.name }.toString().replace("[", "")
                .replace("]", ""),
            storyLine = data.overview.toString()
        )
    }

    private val dummyTvShow = dummyTvList[0]
    private val dummyMovie = dummyMovieList[0]

    private val tvId = dummyTvShow.id
    private val movieId = dummyMovie.id

    @Test
    fun getUpcomingMovies() {
        doAnswer { invocation ->
            (invocation.arguments[0] as LoadContent<List<Movie>>).onContentReceived(dummyMovieList)
            null
        }.`when`(remote).getUpcomingMovies(any())

        val movieList = LiveDataTestUtil.getValue(repository.getUpcomingMovies())

        verify(remote).getUpcomingMovies(any())
        Assert.assertNotNull(movieList)
        Assert.assertEquals(dummyMovieList.size, movieList.size)
    }

    @Test
    fun getMovie() {
        doAnswer { invocation ->
            (invocation.arguments[1] as LoadContent<Movie>).onContentReceived(dummyMovie)
            null
        }.`when`(remote).getMovie(eq(movieId), any())

        val movie = LiveDataTestUtil.getValue(repository.getMovie(movieId))
        verify(remote).getMovie(eq(movieId), any())

        Assert.assertNotNull(movie)
        Assert.assertEquals(dummyMovie.id, movie.id)
        Assert.assertEquals(dummyMovie.title, movie.title)
        Assert.assertEquals(dummyMovie.storyLine, movie.storyLine)
        Assert.assertEquals(dummyMovie.genre, movie.genre)
        Assert.assertEquals(dummyMovie.releaseDate, movie.releaseDate)
        Assert.assertEquals(dummyMovie.imagePosterUrl, movie.imagePosterUrl)
    }

    @Test
    fun getTvOnAir() {
        doAnswer { invocation ->
            (invocation.arguments[0] as LoadContent<List<TvShow>>).onContentReceived(dummyTvList)
            null
        }.`when`(remote).getTvOnAir(any())

        val tvList = LiveDataTestUtil.getValue(repository.getTvOnAir())
        verify(remote).getTvOnAir(any())

        Assert.assertNotNull(tvList)
        Assert.assertEquals(dummyTvList.size, tvList.size)
    }

    @Test
    fun getTv() {
        doAnswer { invocation ->
            (invocation.arguments[1] as LoadContent<TvShow>).onContentReceived(dummyTvShow)
            null
        }.`when`(remote).getTv(eq(tvId), any())

        val tvShow = LiveDataTestUtil.getValue(repository.getTv(tvId))
        verify(remote).getTv(eq(tvId), any())

        Assert.assertNotNull(tvShow)
        Assert.assertEquals(dummyTvShow.id, tvShow.id)
        Assert.assertEquals(dummyTvShow.title, tvShow.title)
        Assert.assertEquals(dummyTvShow.storyLine, tvShow.storyLine)
        Assert.assertEquals(dummyTvShow.genre, tvShow.genre)
        Assert.assertEquals(dummyTvShow.releaseDate, tvShow.releaseDate)
        Assert.assertEquals(dummyTvShow.imagePosterUrl, tvShow.imagePosterUrl)
    }
}