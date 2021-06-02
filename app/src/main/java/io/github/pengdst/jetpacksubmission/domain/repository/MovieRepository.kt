package io.github.pengdst.jetpacksubmission.domain.repository

import androidx.lifecycle.LiveData
import androidx.paging.PagingData
import io.github.pengdst.jetpacksubmission.data.local.room.model.MovieEntity
import io.github.pengdst.jetpacksubmission.data.local.room.model.TvShowEntity
import io.github.pengdst.jetpacksubmission.data.vo.Resource
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

    fun getUpcomingMovies(): LiveData<Resource<PagingData<Movie>>>
    fun getBookmarkedMovies(): LiveData<PagingData<Movie>>
    fun getMovie(movieId: String): LiveData<Resource<Movie>>
    fun getTvOnAir(): LiveData<Resource<PagingData<TvShow>>>
    fun getBookmarkedTvShows(): LiveData<PagingData<TvShow>>
    fun getTv(tvId: String): LiveData<Resource<TvShow>>
    suspend fun setBookmarkedTvShow(tvShowEntity: TvShowEntity)
    suspend fun setBookmarkedMovie(movieEntity: MovieEntity)

}