package io.github.pengdst.jetpacksubmission.data.repository

import androidx.lifecycle.LiveData
import io.github.pengdst.jetpacksubmission.data.source.domain.models.Movie
import io.github.pengdst.jetpacksubmission.data.source.domain.models.TvShow

/**
 * Created on 5/19/21 by Pengkuh Dwi Septiandi (@pengdst)
 *
 * - Github https://github.com/pengdst
 * - Gitlab https://gitlab.com/pengdst
 * - LinkedIn https://linkedin.com/in/pengdst
 */
interface MovieRepository {

    fun getUpcomingMovies(): LiveData<List<Movie>>
    fun getMovie(movieId: String): LiveData<Movie>
    fun getTvOnAir(): LiveData<List<TvShow>>
    fun getTv(tvId: String): LiveData<TvShow>

}