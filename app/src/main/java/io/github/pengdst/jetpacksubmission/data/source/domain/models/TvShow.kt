package io.github.pengdst.jetpacksubmission.data.source.domain.models

import androidx.recyclerview.widget.DiffUtil
import io.github.pengdst.jetpacksubmission.data.constants.ApiConst

/**
 * Created on 5/11/21 by Pengkuh Dwi Septiandi (@pengdst)
 *
 * - Github https://github.com/pengdst
 * - Gitlab https://gitlab.com/pengdst
 * - LinkedIn https://linkedin.com/in/pengdst
 */
data class TvShow(
    val id: String,
    val title: String,
    val posterPath: String,
    val backdropPath: String,
    val releaseDate: String,
    val language: String,
    val genre: String,
    val storyLine: String,
){
    companion object {
        val diffCallback = object : DiffUtil.ItemCallback<TvShow>() {
            override fun areItemsTheSame(oldItem: TvShow, newItem: TvShow) = oldItem.title == newItem.title
            override fun areContentsTheSame(oldItem: TvShow, newItem: TvShow) = oldItem.hashCode() == newItem.hashCode()
        }
    }

    var posterBaseUrl = ApiConst.IMAGE_URL_W500
    var backdropBaseUrl = ApiConst.IMAGE_URL_ORIGINAL
    val imagePosterUrl: String get() = posterBaseUrl+posterPath
    val imageBackdropUrl: String get() = backdropBaseUrl+backdropPath
}
