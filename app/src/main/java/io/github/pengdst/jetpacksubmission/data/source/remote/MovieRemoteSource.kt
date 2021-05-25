package io.github.pengdst.jetpacksubmission.data.source.remote

import io.github.pengdst.jetpacksubmission.data.source.remote.routes.MovieRoute

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

    suspend fun getUpcomingMovies() = movieRoute.getUpcomingMovies()

    suspend fun getMovie(movieId: String) = movieRoute.getMovie(movieId)

    suspend fun getTvOnAir() = movieRoute.getTvOnAir()

    suspend fun getTv(tvId: String) = movieRoute.getTv(tvId)
}