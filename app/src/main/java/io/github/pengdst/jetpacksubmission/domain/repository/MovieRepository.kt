package io.github.pengdst.jetpacksubmission.domain.repository

import io.github.pengdst.jetpacksubmission.domain.models.Movie
import io.github.pengdst.jetpacksubmission.domain.models.TvShow

/**
 * Created on 5/19/21 by Pengkuh Dwi Septiandi (@pengdst)
 *
 * - Github https://github.com/pengdst
 * - Gitlab https://gitlab.com/pengdst
 * - LinkedIn https://linkedin.com/in/pengdst
 */
interface MovieRepository {

    suspend fun getUpcomingMovies(): List<Movie>?
    suspend fun getMovie(movieId: String): Movie?
    suspend fun getTvOnAir(): List<TvShow>?
    suspend fun getTv(tvId: String): TvShow?

}