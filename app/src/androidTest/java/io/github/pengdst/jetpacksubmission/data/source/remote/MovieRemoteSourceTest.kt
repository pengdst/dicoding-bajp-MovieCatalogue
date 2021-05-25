package io.github.pengdst.jetpacksubmission.data.source.remote

import io.github.pengdst.jetpacksubmission.data.source.remote.routes.MovieRoute
import io.github.pengdst.jetpacksubmission.utils.DataStore
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import okhttp3.OkHttpClient
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Test
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

/**
 * Created on 5/25/21 by Pengkuh Dwi Septiandi (@pengdst)
 *
 * - Github https://github.com/pengdst
 * - Gitlab https://gitlab.com/pengdst
 * - LinkedIn https://linkedin.com/in/pengdst
 */

@ExperimentalCoroutinesApi
class MovieRemoteSourceTest {

    private val mockWebServer = MockWebServer()

    private val client = OkHttpClient.Builder()
        .connectTimeout(1, TimeUnit.SECONDS)
        .readTimeout(1, TimeUnit.SECONDS)
        .writeTimeout(1, TimeUnit.SECONDS)
        .build()

    private val api = Retrofit.Builder()
        .baseUrl(mockWebServer.url("/"))
        .client(client)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(MovieRoute::class.java)

    private val remoteSource = MovieRemoteSource(api)

    private val dummyMovieResponse = DataStore.moviesResponse
    private val dummyTvResponse = DataStore.tvShowListResponse
    private val dummyMovie = DataStore.moviesResponse[0]
    private val dummyTv = DataStore.tvShowListResponse[0]
    private val movieId = DataStore.moviesResponse[0].id
    private val tvId = DataStore.tvShowListResponse[0].id

    @Before
    fun setUp() {
        mockWebServer.start()
    }

    @After
    fun tearDown() {
        mockWebServer.shutdown()
    }

    @Test
    fun getUpcomingMovies() {
        mockWebServer.enqueue(
            MockResponse()
                .setResponseCode(200)
                .setBody(DataStore.moviesUpcomingResponseBody)
        )

        runBlocking {
            val actual = remoteSource.getUpcomingMovies().body()?.results
            assertEquals("/movie/upcoming", mockWebServer.takeRequest().path)
            assertNotNull(actual)
            assertEquals(dummyMovieResponse, actual)
        }
    }

    @Test
    fun getMovie() {
        mockWebServer.enqueue(
            MockResponse()
                .setResponseCode(200)
                .setBody(DataStore.movieResponseBody)
        )

        runBlocking {
            val actual = remoteSource.getMovie(movieId.toString()).body()
            assertEquals("/movie/${movieId.toString()}%7D", mockWebServer.takeRequest().path)
            assertNotNull(actual)
            assertEquals(dummyMovie, actual)
        }
    }

    @Test
    fun getTvOnAir() {

        mockWebServer.enqueue(
            MockResponse()
                .setResponseCode(200)
                .setBody(DataStore.tvOnAirResponseBody)
        )

        runBlocking {
            val actual = remoteSource.getTvOnAir().body()?.results
            assertEquals("/tv/on_the_air", mockWebServer.takeRequest().path)
            assertNotNull(actual)
            assertEquals(dummyTvResponse, actual)
        }
    }

    @Test
    fun getTv() {
        mockWebServer.enqueue(
            MockResponse()
                .setResponseCode(200)
                .setBody(DataStore.tvShowResponseBody)
        )

        runBlocking {
            val actual = remoteSource.getTv(tvId.toString()).body()
            assertEquals("/tv/${tvId.toString()}%7D", mockWebServer.takeRequest().path)
            assertNotNull(actual)
            assertEquals(dummyTv, actual)
        }
    }
}