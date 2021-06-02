package io.github.pengdst.jetpacksubmission.data.local.source

import io.github.pengdst.jetpacksubmission.data.local.room.dao.MovieDao
import io.github.pengdst.jetpacksubmission.data.local.room.model.MovieEntity

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
    fun saveMovie(movieEntity: MovieEntity) {
        movieDao.insertMovie(movieEntity)
    }

}