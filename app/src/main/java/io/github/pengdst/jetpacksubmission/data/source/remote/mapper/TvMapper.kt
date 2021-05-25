package io.github.pengdst.jetpacksubmission.data.source.remote.mapper

import io.github.pengdst.jetpacksubmission.data.source.domain.models.Movie
import io.github.pengdst.jetpacksubmission.data.source.domain.models.TvShow
import io.github.pengdst.jetpacksubmission.data.source.remote.models.MovieDto
import io.github.pengdst.jetpacksubmission.data.source.remote.models.TvDto

/**
 * Created on 5/25/21 by Pengkuh Dwi Septiandi (@pengdst)
 *
 * - Github https://github.com/pengdst
 * - Gitlab https://gitlab.com/pengdst
 * - LinkedIn https://linkedin.com/in/pengdst
 */
object TvMapper {

    fun TvDto.toDomain() = TvShow(
        id = id.toString(),
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

    fun List<TvDto>.toDomain() = map {
        it.toDomain()
    }
}