package io.github.pengdst.jetpacksubmission.data.local.source

import androidx.lifecycle.LiveData
import androidx.paging.*
import io.github.pengdst.jetpacksubmission.data.local.room.dao.MovieDao
import io.github.pengdst.jetpacksubmission.data.local.room.model.MovieEntity
import io.github.pengdst.jetpacksubmission.data.local.room.model.TvShowEntity

/**
 * Created on 6/1/21 by Pengkuh Dwi Septiandi (@pengdst)
 *
 * - Github https://github.com/pengdst
 * - Gitlab https://gitlab.com/pengdst
 * - LinkedIn https://linkedin.com/in/pengdst
 */

class MovieLocalSource(
    private val movieDao: MovieDao
) {

    fun getAllMovies(): LiveData<PagingData<MovieEntity>> {
        val config = PagingConfig(
            pageSize = 4,
            initialLoadSize = 4,
        )
        return Pager(
            config = config, pagingSourceFactory = movieDao.getMovies().asPagingSourceFactory()
        ).liveData
    }
    fun getBookmarkedMovies(): LiveData<PagingData<MovieEntity>> {
        val config = PagingConfig(
            pageSize = 4,
            initialLoadSize = 4,
        )
        return Pager(
            config = config, pagingSourceFactory = movieDao.getBookmarkedMovie().asPagingSourceFactory()
        ).liveData
    }
    fun getMovie(movieId: String) = movieDao.getMovieById(movieId)
    suspend fun saveMovie(movieEntity: MovieEntity) {
        movieDao.insertMovie(movieEntity)
    }
    suspend fun saveMovies(list: List<MovieEntity>) {
        movieDao.insertMovies(list)
    }

    fun getBookmarkedTvShows(): LiveData<PagingData<TvShowEntity>> {
        val config = PagingConfig(
            pageSize = 4,
            initialLoadSize = 4,
        )
        return Pager(
            config = config, pagingSourceFactory = movieDao.getBookmarkedTvShow().asPagingSourceFactory()
        ).liveData
    }

    fun getTvShows(): LiveData<PagingData<TvShowEntity>> {
        val config = PagingConfig(
            pageSize = 4,
            initialLoadSize = 4,
        )
        return Pager(
            config = config, pagingSourceFactory = movieDao.getTvShows().asPagingSourceFactory()
        ).liveData
    }
    fun getTv(tvId: String) = movieDao.getTvShow(tvId)
    suspend fun saveTvShow(tvShow: TvShowEntity) {
        movieDao.insertTvShow(tvShow)
    }
    suspend fun saveTvShows(list: List<TvShowEntity>) {
        movieDao.insertTvShows(list)
    }

    suspend fun updateTvShow(tvShowEntity: TvShowEntity) {
        movieDao.updateTvShow(tvShowEntity)
    }

    suspend fun updateMovie(movieEntity: MovieEntity) {
        movieDao.updateMovie(movieEntity)
    }

}