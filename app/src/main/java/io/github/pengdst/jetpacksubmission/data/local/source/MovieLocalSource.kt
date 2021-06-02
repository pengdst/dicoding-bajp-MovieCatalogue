package io.github.pengdst.jetpacksubmission.data.local.source

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

    fun getAllMovies() = movieDao.getMovies()
    fun getMovie(movieId: String) = movieDao.getMovieById(movieId)
    suspend fun saveMovie(movieEntity: MovieEntity) {
        movieDao.insertMovie(movieEntity)
    }
    suspend fun saveMovies(list: List<MovieEntity>) {
        movieDao.insertMovies(list)
    }

    fun getTvShows() = movieDao.getTvShows()
    fun getTv(tvId: String) = movieDao.getTvShow(tvId)
    suspend fun saveTvShow(tvShow: TvShowEntity) {
        movieDao.insertTvShow(tvShow)
    }
    suspend fun saveTvShows(list: List<TvShowEntity>) {
        movieDao.insertTvShows(list)
    }

}