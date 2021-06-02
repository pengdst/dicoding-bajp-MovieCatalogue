package io.github.pengdst.jetpacksubmission.data.local.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import io.github.pengdst.jetpacksubmission.data.local.room.dao.MovieDao
import io.github.pengdst.jetpacksubmission.data.local.room.model.MovieEntity
import io.github.pengdst.jetpacksubmission.data.local.room.model.TvShowEntity

/**
 * Created on 6/2/21 by Pengkuh Dwi Septiandi (@pengdst)
 *
 * - Github https://github.com/pengdst
 * - Gitlab https://gitlab.com/pengdst
 * - LinkedIn https://linkedin.com/in/pengdst
 */

@Database(entities = [TvShowEntity::class, MovieEntity::class], version = 1)
abstract class MovieRoomDatabase : RoomDatabase() {
    abstract fun movieDao(): MovieDao

    companion object {
        @Volatile
        private var INSTANCE: MovieRoomDatabase? = null

        fun newInstance(context: Context): MovieRoomDatabase {
            return INSTANCE ?: synchronized(MovieRoomDatabase::class.java) {
                Room.databaseBuilder(
                    context.applicationContext,
                    MovieRoomDatabase::class.java, "movie_database"
                ).build().also {
                    INSTANCE = it
                }
            }
        }
    }
}