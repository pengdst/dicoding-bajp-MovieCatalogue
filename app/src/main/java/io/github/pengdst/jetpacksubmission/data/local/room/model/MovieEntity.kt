package io.github.pengdst.jetpacksubmission.data.local.room.model

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Created on 5/11/21 by Pengkuh Dwi Septiandi (@pengdst)
 *
 * - Github https://github.com/pengdst
 * - Gitlab https://gitlab.com/pengdst
 * - LinkedIn https://linkedin.com/in/pengdst
 */

@Entity(tableName = "movies")
data class MovieEntity(
    @PrimaryKey(autoGenerate = false)
    val id: String,
    val title: String,
    val posterPath: String,
    val backdropPath: String,
    val releaseDate: String,
    val language: String,
    val genre: String,
    val storyLine: String,
    var bookmarked: Boolean
)
