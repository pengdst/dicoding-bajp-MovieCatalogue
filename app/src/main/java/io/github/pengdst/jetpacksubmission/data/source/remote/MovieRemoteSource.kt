package io.github.pengdst.jetpacksubmission.data.source.remote

import io.github.pengdst.jetpacksubmission.data.source.remote.models.MovieDto
import io.github.pengdst.jetpacksubmission.data.source.remote.models.TvDto
import io.github.pengdst.jetpacksubmission.data.source.remote.response.MovieResponse
import io.github.pengdst.jetpacksubmission.data.source.remote.response.TvResponse
import io.github.pengdst.jetpacksubmission.data.source.remote.routes.MovieRoute
import io.github.pengdst.jetpacksubmission.utils.EspressoIdlingResource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response

/**
 * Created on 5/20/21 by Pengkuh Dwi Septiandi (@pengdst)
 *
 * - Github https://github.com/pengdst
 * - Gitlab https://gitlab.com/pengdst
 * - LinkedIn https://linkedin.com/in/pengdst
 */
class MovieRemoteSource(
    private val movieRoute: MovieRoute
) {

    suspend fun getUpcomingMovies(): Response<MovieResponse> {
        EspressoIdlingResource.increment()
        return withContext(Dispatchers.IO){
            EspressoIdlingResource.decrement()
            movieRoute.getUpcomingMovies()
        }
    }

    suspend fun getMovie(movieId: String): Response<MovieDto> {
        EspressoIdlingResource.increment()
        return withContext(Dispatchers.IO){
            EspressoIdlingResource.decrement()
            movieRoute.getMovie(movieId)
        }
    }

    suspend fun getTvOnAir(): Response<TvResponse> {
        EspressoIdlingResource.increment()
        return withContext(Dispatchers.IO){
            EspressoIdlingResource.decrement()
            movieRoute.getTvOnAir()
        }
    }

    suspend fun getTv(tvId: String): Response<TvDto> {
        EspressoIdlingResource.increment()
        return withContext(Dispatchers.IO){
            EspressoIdlingResource.decrement()
            movieRoute.getTv(tvId)
        }
    }
}