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
    var id: Int,
    var title: String,
    var posterPath: String,
    var backdropPath: String,
    var releaseDate: String,
    var language: String,
    var genre: String,
    var storyLine: String
){

    var isFavourite: Boolean = false

}
