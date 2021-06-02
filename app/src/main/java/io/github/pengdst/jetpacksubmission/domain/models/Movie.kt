package io.github.pengdst.jetpacksubmission.domain.models

import androidx.recyclerview.widget.DiffUtil
import io.github.pengdst.jetpacksubmission.data.constants.ApiConst.IMAGE_URL_ORIGINAL
import io.github.pengdst.jetpacksubmission.data.constants.ApiConst.IMAGE_URL_W500

/**
 * Created on 5/11/21 by Pengkuh Dwi Septiandi (@pengdst)
 *
 * - Github https://github.com/pengdst
 * - Gitlab https://gitlab.com/pengdst
 * - LinkedIn https://linkedin.com/in/pengdst
 */
data class Movie(
    val id: String,
    val title: String,
    val posterPath: String,
    val backdropPath: String,
    val releaseDate: String,
    val language: String,
    val genre: String,
    val storyLine: String,
) {
    companion object {
        val diffCallback = object : DiffUtil.ItemCallback<Movie>() {
            override fun areItemsTheSame(oldItem: Movie, newItem: Movie) =
                oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: Movie, newItem: Movie) =
                oldItem == newItem
        }
    }

    var posterBaseUrl = IMAGE_URL_W500
    var backdropBaseUrl = IMAGE_URL_ORIGINAL
    val imagePosterUrl: String get() = posterBaseUrl + posterPath
    val imageBackdropUrl: String get() = backdropBaseUrl + backdropPath
}
