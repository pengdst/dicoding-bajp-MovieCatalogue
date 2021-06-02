package io.github.pengdst.jetpacksubmission.data.local.mapper

import io.github.pengdst.jetpacksubmission.data.local.room.model.MovieEntity
import io.github.pengdst.jetpacksubmission.domain.models.Movie

/**
* Created on 6/2/21 by Pengkuh Dwi Septiandi (@pengdst)
*
* - Github https://github.com/pengdst
* - Gitlab https://gitlab.com/pengdst
* - LinkedIn https://linkedin.com/in/pengdst
*/
object MovieMapper {

    fun MovieEntity.toDomain() = Movie(
        id = id,
        title = title,
        posterPath = posterPath,
        backdropPath = backdropPath,
        releaseDate = releaseDate,
        language = language,
        genre = genre,
        storyLine = storyLine
    )

    fun Movie.toEntity() = MovieEntity(
        id = id,
        title = title,
        posterPath = posterPath,
        backdropPath = backdropPath,
        releaseDate = releaseDate,
        language = language,
        genre = genre,
        storyLine = storyLine,
        bookmarked = false
    )

    fun List<MovieEntity>.toDomain() = map { it.toDomain() }
}