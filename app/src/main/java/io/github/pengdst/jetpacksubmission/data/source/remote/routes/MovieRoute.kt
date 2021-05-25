package io.github.pengdst.jetpacksubmission.data.source.remote.routes

import io.github.pengdst.jetpacksubmission.data.source.remote.models.MovieDto
import io.github.pengdst.jetpacksubmission.data.source.remote.models.TvDto
import io.github.pengdst.jetpacksubmission.data.source.remote.response.MovieResponse
import io.github.pengdst.jetpacksubmission.data.source.remote.response.TvResponse
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * Created on 5/19/21 by Pengkuh Dwi Septiandi (@pengdst)
 *
 * - Github https://github.com/pengdst
 * - Gitlab https://gitlab.com/pengdst
 * - LinkedIn https://linkedin.com/in/pengdst
 */

interface MovieRoute {

    @GET("movie/upcoming")
    suspend fun getUpcomingMovies(): Response<MovieResponse>

    @GET("movie/{movie_id}}")
    suspend fun getMovie(
        @Path("movie_id") movieId: String,
    ): Response<MovieDto>

    @GET("tv/on_the_air")
    suspend fun getTvOnAir(): Response<TvResponse>

    @GET("tv/{tv_id}}")
    suspend fun getTv(
        @Path("tv_id") tvId: String,
    ): Response<TvDto>

}