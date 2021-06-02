package io.github.pengdst.jetpacksubmission.data.local.room.dao

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import androidx.room.*
import io.github.pengdst.jetpacksubmission.data.local.room.model.MovieEntity
import io.github.pengdst.jetpacksubmission.data.local.room.model.TvShowEntity

/**
 * Created on 5/28/21 by Pengkuh Dwi Septiandi (@pengdst)
 *
 * - Github https://github.com/pengdst
 * - Gitlab https://gitlab.com/pengdst
 * - LinkedIn https://linkedin.com/in/pengdst
 */

@Dao
interface MovieDao {

    @Query("SELECT * FROM movies")
    fun getMovies(): DataSource.Factory<Int, MovieEntity>

    @Query("SELECT * FROM movies where isFavourite = 1")
    fun getBookmarkedMovie(): DataSource.Factory<Int, MovieEntity>

    @Transaction
    @Query("SELECT * FROM movies WHERE id = :id")
    fun getMovieById(id: String): LiveData<MovieEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMovies(movies: List<MovieEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMovie(movie: MovieEntity)

    @Update
    suspend fun updateMovie(movie: MovieEntity)

    @Query("SELECT * FROM tv_shows")
    fun getTvShows(): DataSource.Factory<Int, TvShowEntity>

    @Query("SELECT * FROM tv_shows where isFavourite = 1")
    fun getBookmarkedTvShow(): DataSource.Factory<Int, TvShowEntity>

    @Query("SELECT * FROM tv_shows WHERE id = :id")
    fun getTvShow(id: String): LiveData<TvShowEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTvShows(tvShow: List<TvShowEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTvShow(tvShow: TvShowEntity)

    @Update
    suspend fun updateTvShow(tvShow: TvShowEntity)

}