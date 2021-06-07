package io.github.pengdst.jetpacksubmission.domain.models

import androidx.recyclerview.widget.DiffUtil
import io.github.pengdst.jetpacksubmission.data.constants.ApiConst
import java.util.*

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
    val isFavourite: Boolean = false,
    var createdAt: Long = Date().time,
    var updatedAt: Long = Date().time
) {
    companion object {
        val diffCallback = object : DiffUtil.ItemCallback<TvShow>() {
            override fun areItemsTheSame(oldItem: TvShow, newItem: TvShow) =
                oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: TvShow, newItem: TvShow) =
                oldItem == newItem
        }
    }

    @Suppress("MemberVisibilityCanBePrivate")
    var posterBaseUrl = ApiConst.IMAGE_URL_W500
    @Suppress("MemberVisibilityCanBePrivate")
    var backdropBaseUrl = ApiConst.IMAGE_URL_ORIGINAL
    val imagePosterUrl: String get() = posterBaseUrl + posterPath
    val imageBackdropUrl: String get() = backdropBaseUrl + backdropPath
}
