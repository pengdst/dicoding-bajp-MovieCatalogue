package io.github.pengdst.jetpacksubmission.data.remote.mapper

import io.github.pengdst.jetpacksubmission.data.local.room.model.TvShowEntity
import io.github.pengdst.jetpacksubmission.data.remote.retrofit.models.TvDto

/**
 * Created on 5/25/21 by Pengkuh Dwi Septiandi (@pengdst)
 *
 * - Github https://github.com/pengdst
 * - Gitlab https://gitlab.com/pengdst
 * - LinkedIn https://linkedin.com/in/pengdst
 */
object TvDtoMapper {

    fun TvDto.toEntity() = TvShowEntity(
        id = id ?: 0,
        title = name.toString(),
        backdropPath = backdropPath.toString(),
        posterPath = posterPath.toString(),
        releaseDate = firstAirDate.toString(),
        language = spokenLanguages?.map { it.englishName }.toString()
            .replace("[", "").replace("]", ""),
        genre = genres?.map { it.name }.toString().replace("[", "")
            .replace("]", ""),
        storyLine = overview.toString()
    )

    fun List<TvDto>.toEntity() = map {
        it.toEntity()
    }
}