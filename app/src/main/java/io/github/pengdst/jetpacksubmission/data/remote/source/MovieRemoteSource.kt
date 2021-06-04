package io.github.pengdst.jetpacksubmission.data.remote.source

import io.github.pengdst.jetpacksubmission.data.remote.retrofit.models.MovieDto
import io.github.pengdst.jetpacksubmission.data.remote.retrofit.models.TvDto
import io.github.pengdst.jetpacksubmission.data.remote.retrofit.response.MovieResponse
import io.github.pengdst.jetpacksubmission.data.remote.retrofit.response.TvResponse
import io.github.pengdst.jetpacksubmission.data.remote.retrofit.routes.MovieRoute
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
            movieRoute.getUpcomingMovies().also {
                EspressoIdlingResource.decrement()
            }
        }
    }

    suspend fun getMovie(movieId: String): Response<MovieDto> {
        EspressoIdlingResource.increment()
        return withContext(Dispatchers.IO){
            movieRoute.getMovie(movieId).also {
                EspressoIdlingResource.decrement()
            }
        }
    }

    suspend fun getTvOnAir(): Response<TvResponse> {
        EspressoIdlingResource.increment()
        return withContext(Dispatchers.IO){
            movieRoute.getTvOnAir().also {
                EspressoIdlingResource.decrement()
            }
        }
    }

    suspend fun getTv(tvId: String): Response<TvDto> {
        EspressoIdlingResource.increment()
        return withContext(Dispatchers.IO){
            movieRoute.getTv(tvId).also {
                EspressoIdlingResource.decrement()
            }
        }
    }
}