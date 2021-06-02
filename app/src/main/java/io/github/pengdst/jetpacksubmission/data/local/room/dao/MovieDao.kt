package io.github.pengdst.jetpacksubmission.data.local.room.dao

import androidx.lifecycle.LiveData
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
    fun getMovies(): LiveData<List<MovieEntity>>

    @Query("SELECT * FROM movies where bookmarked = 1")
    fun getBookmarkedMovie(): LiveData<List<MovieEntity>>

    @Transaction
    @Query("SELECT * FROM movies WHERE id = :id")
    fun getMovieById(id: String): LiveData<MovieEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMovies(courses: List<MovieEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMovie(course: MovieEntity)

    @Update
    fun updateMovie(course: MovieEntity)

    @Query("SELECT * FROM tv_shows WHERE id = :id")
    fun getTvShowsBy(id: String): LiveData<List<TvShowEntity>>

    @Query("SELECT * FROM tv_shows WHERE id = :id")
    fun getTvShowById(id: String): LiveData<TvShowEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertTvShows(module: List<TvShowEntity>)

    @Update
    fun updateTvShow(module: TvShowEntity)

}