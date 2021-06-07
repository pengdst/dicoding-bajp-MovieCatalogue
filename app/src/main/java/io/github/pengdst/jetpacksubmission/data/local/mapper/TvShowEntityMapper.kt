package io.github.pengdst.jetpacksubmission.data.local.mapper

import io.github.pengdst.jetpacksubmission.data.local.room.model.TvShowEntity
import io.github.pengdst.jetpacksubmission.domain.models.TvShow

/**
* Created on 6/2/21 by Pengkuh Dwi Septiandi (@pengdst)
*
* - Github https://github.com/pengdst
* - Gitlab https://gitlab.com/pengdst
* - LinkedIn https://linkedin.com/in/pengdst
*/
object TvShowEntityMapper {

    fun TvShowEntity.toDomain() = TvShow(
        id = id.toString(),
        title = title,
        posterPath = posterPath,
        backdropPath = backdropPath,
        releaseDate = releaseDate,
        language = language,
        genre = genre,
        storyLine = storyLine,
        isFavourite = isFavourite,
        createdAt = createdAt,
        updatedAt = updatedAt
    )

    fun TvShow.toEntity() = TvShowEntity(
        id = id.toInt(),
        title = title,
        posterPath = posterPath,
        backdropPath = backdropPath,
        releaseDate = releaseDate,
        language = language,
        genre = genre,
        storyLine = storyLine,
    ).apply {
        isFavourite = this@toEntity.isFavourite
        createdAt = this@toEntity.createdAt
        updatedAt = this@toEntity.updatedAt
    }

    fun List<TvShowEntity>.toDomain() = map { it.toDomain() }
}